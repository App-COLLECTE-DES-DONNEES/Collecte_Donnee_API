package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.ActionResp;
import com.ditros.mcd.model.entity.PersonAction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ActionMapper {

    public ActionResp fromAction(PersonAction action) {
        if(action==null) return null;
        ActionResp actionResp = new ActionResp();
        BeanUtils.copyProperties(action, actionResp);

        return actionResp;
    }

}
