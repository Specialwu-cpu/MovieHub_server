package com.moviehub.server.service.impl;

import com.moviehub.server.entity.verifyCodeTable;
import com.moviehub.server.repository.VerifyCodeRepository;
import com.moviehub.server.service.IVerifyCodeService;
import com.moviehub.server.util.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

    @Resource
    private VerifyCodeRepository repository;
    @Override
    public boolean sendVerifyCode(String toEmail) throws Exception {
        if (Email.isEmail(toEmail)) {
            String thisCode = verifyCode.getVerifyCode();
            boolean success = Email.sendEmail(thisCode, new String[]{toEmail});
            //发送成功了要 记到表里面为了后来   是否注册成功查询用
            if (success) {
                verifyCodeTable verifyCodeRecord = new verifyCodeTable();
                verifyCodeRecord.setRegister_id(toEmail);
                verifyCodeRecord.setVerify_code(thisCode);
                verifyCodeRecord.setCode_time(TimeManager.getNowDateTime());
                repository.save(verifyCodeRecord);
                return true;
                //发送成功
            }
            return false;
            //代表发送不成功
        }
        System.out.println("666");
        return false;
        //代表不是邮箱
    }

    @Override
    public boolean wrongVerifyCode(String mail_or_id, String verifyCode) {
        Optional<verifyCodeTable> thisRecord = repository.findById(mail_or_id);
        if (!thisRecord.isPresent()) {
            return true;
        }
        verifyCodeTable verifyCodeRecode = thisRecord.get();
        if (!verifyCode.equals(verifyCodeRecode.getVerify_code())) {
            return true;
        }
        if (TimeManager.getLapseHitherto(verifyCodeRecode.getCode_time()) > 3000) {
            return true;
        }
        return false;
    }
}
