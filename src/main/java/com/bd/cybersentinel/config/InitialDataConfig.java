package com.bd.cybersentinel.config;

import com.bd.cybersentinel.entity.User;
import com.bd.cybersentinel.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
public class InitialDataConfig implements ApplicationRunner {

	private static final String SYSTEM_ADMIN_USERNAME = "admin";
	private static final String SYSTEM_ADMIN_FULL_NAME = "System Admin";
	private static final String SYSTEM_ADMIN_EMAIL = "admin@cyber-sentinel.com";
	private static final String SYSTEM_ADMIN_PASSWORD = "admin@2025";
	private static final String SYSTEM_ADMIN_MOBILE = "+880 1700055511";
	private static final String SYSTEM_ADMIN_PHONE = "55434544476";

	@Autowired private UserRepo userRepo;
	@Autowired private BCryptPasswordEncoder encoder;

	@Transactional
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("===========> Search System admin user info from DB ===========");
		User uo = userRepo.findByUsername(SYSTEM_ADMIN_USERNAME);
		if(uo != null) {
			log.info("===========> System Admin found ===========");
		} else {
			log.info("===========> System Admin not found ===========");
			log.info("===========> Creating System Admin ===========");
			User u = new User();
			u.setUsername(SYSTEM_ADMIN_USERNAME);
			u.setFullName(SYSTEM_ADMIN_FULL_NAME);
			u.setEmail(SYSTEM_ADMIN_EMAIL);
			u.setMobile(SYSTEM_ADMIN_MOBILE);
			u.setPhone(SYSTEM_ADMIN_PHONE);
			u.setPassword(encoder.encode(SYSTEM_ADMIN_PASSWORD));
			u.setActive(true);
			u.setSuperAdmin(true);
			u.setLocked(false);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 1);
			u.setExpiryDate(cal.getTime());
			u.setCreatedBy(SYSTEM_ADMIN_USERNAME);
			u.setCreatedOn(new Date());
			u.setUpdatedBy(SYSTEM_ADMIN_USERNAME);

			u = userRepo.save(u);
			log.info("===========> System Admin Created ===========");
		}

	}
}
