package com.moviehub.server.service;

import com.moviehub.server.util.BaseResponse;

public interface IVerifyCodeService {

    BaseResponse sendVerifyCode(String toEmail) throws Exception;

    boolean wrongVerifyCode(String mail_or_id, String verifyCode);
}
