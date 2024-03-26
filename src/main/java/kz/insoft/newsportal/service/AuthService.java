package kz.insoft.newsportal.service;

import kz.insoft.newsportal.dto.RegisterDTO;

public interface AuthService {

    String registerUser(RegisterDTO registerDTO);
}
