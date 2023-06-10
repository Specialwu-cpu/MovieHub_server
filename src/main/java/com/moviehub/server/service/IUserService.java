package com.moviehub.server.service;

import com.moviehub.server.entity.User;
import com.moviehub.server.util.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @Project ：server
 * @File ：IUserService.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/3/15 10:06
 **/

public interface IUserService {
    BaseResponse login(String mail_or_id, String password) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

    BaseResponse register(String mail_or_id, String password, String user_name, String verify_code);

    User save(String mail_or_id, String user_name, String password);

    List<User> findAll();

    boolean emailInDatabase(String mail_or_id);

    List<User> emailPasswordLogin(String mail_or_id, String password);

    boolean invalidPassword(String password);

    boolean invalidUserName(String userName);

    BaseResponse getUserInfo(String mailOrId);

    BaseResponse getUserHistory(String mailOrId, int page);

    BaseResponse addUserHistory(String mailOrId, Long tmdbId);

    BaseResponse deleteUserHistory(String mailOrId, Long historyId);

    BaseResponse getUserCollection(String mailOrId, int page);

    BaseResponse addUserCollection(String mailOrId, Long tmdbId);

    BaseResponse deleteUserCollection(String mailOrId, Long collectionId);

    BaseResponse resetPassword(String mailOrId, String oldPasswordOne, String oldPasswordTwo, String newPassword);

    BaseResponse forgetPassword(String mailOrId, String newPasswordOne, String newPasswordTwo, String verifyCode);

    BaseResponse updateUser(String mailOrId, String userName, String styleText, MultipartFile file);

    BaseResponse updateAvatar(String mailOrId, MultipartFile file);
}
