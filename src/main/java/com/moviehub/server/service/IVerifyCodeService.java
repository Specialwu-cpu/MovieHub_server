package com.moviehub.server.service;

import com.moviehub.server.util.BaseResponse;

public interface IVerifyCodeService {

    public BaseResponse<Object> sendVerifyCode(String toEmail) throws Exception;

    public boolean wrongVerifyCode(String mail_or_id, String verifyCode);
}
