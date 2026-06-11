package com.mgkj.service.auth;


import com.mgkj.vo.auth.MesPermission;
import com.mgkj.vo.auth.YFPermission;

public interface YFPermissionService {

    YFPermission getPermissonOnYF(String username);

    MesPermission getMesPermission(String username);
}
