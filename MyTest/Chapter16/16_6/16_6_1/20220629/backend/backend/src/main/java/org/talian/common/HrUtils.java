package org.talian.common;

import org.springframework.security.core.context.SecurityContextHolder;
import org.talian.bean.Hr;

public class HrUtils {
    public static Hr getCurrentHr(){
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
