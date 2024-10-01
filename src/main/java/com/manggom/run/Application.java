package com.manggom.run;

import com.manggom.model.dto.ExitDTO;
import com.manggom.play.InputUserInfo;
import com.manggom.play.SelectAllUser;


public class Application {

    public static void main(String[] args) {
        InputUserInfo start = new InputUserInfo();
        ExitDTO exit = new ExitDTO();

//        SelectAllUser se = new SelectAllUser();
//        se.selectAllUser();

        for(int i = 0; true; i++){
            start.inputUserInfo();
            if(exit.getExit() == 1){
                break;
            }
        }
    }
}
