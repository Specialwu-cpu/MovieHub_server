package com.moviehub.server.service;

public interface IVerifyCodeService {

    public boolean sendVerifyCode(String toEmail) throws Exception;

    public boolean wrongVerifyCode(String mail_or_id, String verifyCode);
}
