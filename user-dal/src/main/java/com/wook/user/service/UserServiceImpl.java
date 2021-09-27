package com.wook.user.service;

import com.wook.user.config.LoggerConfiguration;
import com.wook.user.exception.MessageException;
import com.wook.user.model.db.PhoneDb;
import com.wook.user.model.db.UserDb;
import com.wook.user.model.dto.PhoneDto;
import com.wook.user.model.dto.ResponseDto;
import com.wook.user.model.dto.UserDto;
import com.wook.user.repository.PhoneRepository;
import com.wook.user.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;

	private LoggerConfiguration logger = LoggerConfiguration.getLogger("UserServicesImpl");

	private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public ResponseDto saveUser(UserDto userDto) {
		logger.info("saveUser - start");

		userDto.setToken(UUID.randomUUID().toString());
		userDto.setCreateDate(new Date());
		userDto.setLastLogin(new Date());
		userDto.setUpdateDate(new Date());
		
		UserDb entity = convertToEntity(userDto);
		UserDb responseDataBase = new UserDb();
		ResponseDto response = new ResponseDto();
		
		try {
			
			responseDataBase = userRepository.save(entity);
			
			response.setId(responseDataBase.getId());
			response.setCreateDate(responseDataBase.getCreateDate());
			response.setUpdateDate(responseDataBase.getUpdateDate());
			response.setLastLogin(responseDataBase.getLastLogin());
			response.setToken(responseDataBase.getToken());
			response.setActive(responseDataBase.isActive());
			
		} catch (Exception e) {
			logger.error("message ::: '" + e.getMessage() + "'");
			logger.error("cause ::: '" +e.getCause() + "'");
			throw new MessageException("user.error.put");
		}

		return response;
	}

	@Override
	public UserDto delete(Long id) {
		Optional<UserDb> deleteObject = userRepository.findById(id);
		userRepository.deleteById(id);
		return convertToDto(deleteObject.get());
	}

	public UserDto findById(Long id) {
		logger.info("findById - start");
		Optional<UserDb> user = null;
		try {
			user = userRepository.findById(id);
			
			if (user.get() == null) {
				logger.info("findById - not found");
				throw new MessageException(messageSource.getMessage("user.validate.norfound", null, Locale.US));
			}

		} catch (Exception e) {
			logger.info("findById - not found");
			throw new MessageException(messageSource.getMessage("user.validate.norfound", null, Locale.US));
		}
		return convertToDto(user.get());
	}
	
	public ResponseDto loginUser(UserDto userDto) {
		logger.info("loginUser - start");
		Optional<UserDb> user = null;
		ResponseDto response = new ResponseDto();
		try {

			user = userRepository.findById(Long.parseLong(userDto.getId()));
			
			if (user.get().isActive() == true 
					&& user.get().getToken().equalsIgnoreCase(userDto.getToken()) == true
					&& user.get().getPassword().compareTo(userDto.getPassword()) == 0) {
				
			    logger.info("user.getId() ::: " + user.get().getId());
				userRepository.updateLastLogin(new Date(), user.get().getId(), user.get().getToken());
				
				response.setLastLogin(new Date());
				response.setId(user.get().getId());
				response.setToken(user.get().getToken());
				response.setActive(user.get().isActive());
				response.setCreateDate(user.get().getCreateDate());
				response.setUpdateDate(user.get().getUpdateDate());
			} else {
				logger.info("loginUser - unprocessable request");
				throw new MessageException(messageSource.getMessage("user.validation.unprocessable", null, Locale.US));
			}
			
		} catch (Exception e) {
			logger.info("findById - not found");
			e.printStackTrace();
			throw new MessageException(messageSource.getMessage("user.validate.norfound", null, Locale.US));
		}
		return response;
	}
	
	public ResponseDto updateUser(UserDto userDto) {
		logger.info("updateUser - start");
		ResponseDto response = new ResponseDto();
		Optional<UserDb> user = null;
		List<PhoneDb> phoneList = new ArrayList<>();
		
		try {
			
			user = userRepository.findById(Long.parseLong(userDto.getId()));
			
			logger.info(user.get().toString());
			logger.info(userDto.toString());
			
			if (user.get().isActive() == true && userDto.isActive() == true 
					&& user.get().getToken().equalsIgnoreCase(userDto.getToken()) == true) {
				
				userRepository.updateUser(userDto.getName(), userDto.getEmail(), userDto.getPassword(), userDto.isActive(), new Date(), Long.parseLong(userDto.getId()), userDto.getToken());
				
				phoneRepository.deletePhoneByUserId(user.get().getId());
				
				phoneList = convertToEntityPhoneDtoListToPhoneDbList(userDto.getPhones());
				for (Iterator<PhoneDb> iterator = phoneList.iterator(); iterator.hasNext();) {
					PhoneDb phone = (PhoneDb) iterator.next();
					logger.info(phone.getNumber() + " " + phone.getCountryCode() + " " + phone.getCityCode());
					phone.setUserId(Long.parseLong(userDto.getId()));
					phoneRepository.save(phone);
				}
				
				response.setLastLogin(user.get().getLastLogin());
				response.setId(user.get().getId());
				response.setToken(user.get().getToken());
				response.setActive(user.get().isActive());
				response.setCreateDate(user.get().getCreateDate());
				response.setUpdateDate(new Date());
				
			} else {
				logger.info("loginUser - unprocessable request");
				throw new MessageException(messageSource.getMessage("user.validation.unprocessable", null, Locale.US));
			}
			
		} catch (Exception e) {
			logger.info("findById - not found");
			e.printStackTrace();
			throw new MessageException(messageSource.getMessage("user.validate.norfound", null, Locale.US));
		}
		
		return response;
	}

	//convierte el objeto de base de datos a UserDto
	private UserDto convertToDto(UserDb userConsumer) {
		UserDto userConsumerDto = modelMapper.map(userConsumer, UserDto.class);
		List<PhoneDto> listPhone = new ArrayList<>();
		
		for (Iterator<PhoneDb> iterator = userConsumer.getPhone().iterator(); iterator.hasNext();) {
			PhoneDto phoneDto =  modelMapper.map(iterator.next(), PhoneDto.class);
			listPhone.add(phoneDto);
		}
		userConsumerDto.setPhones(listPhone);
		return userConsumerDto;
	}
	
	//convierte userDto a entity 
	private UserDb convertToEntity(UserDto useInput) {
		UserDb userConsumer = modelMapper.map(useInput, UserDb.class);
		List<PhoneDb> listPhone = new ArrayList<>();
		for (Iterator<PhoneDto> iterator = useInput.getPhones().iterator(); iterator.hasNext();) {
			PhoneDto phone = iterator.next();
			listPhone.add(modelMapper.map(phone, PhoneDb.class));
		}
		userConsumer.setPhone(listPhone);
		return userConsumer;
	}
	
	private List<PhoneDb> convertToEntityPhoneDtoListToPhoneDbList(List<PhoneDto> phoneInput) {
		List<PhoneDb> listPhone = new ArrayList<>();
		for (Iterator<PhoneDto> iterator = phoneInput.iterator(); iterator.hasNext();) {
			PhoneDto phone = iterator.next();
			listPhone.add(modelMapper.map(phone, PhoneDb.class));
		}
		return listPhone;
	}

}
