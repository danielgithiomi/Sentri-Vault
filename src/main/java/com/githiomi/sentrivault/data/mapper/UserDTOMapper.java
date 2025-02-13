package com.githiomi.sentrivault.data.mapper;

import com.githiomi.sentrivault.data.dto.UserDTO;
import com.githiomi.sentrivault.data.domain.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: https://github.com/githiomi
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/

@Component
public class UserDTOMapper {

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    public static User fromUserDTO(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

}
