package com.ade.step2.model.local;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by aderifaldi on 2017-12-24.
 */
@NoArgsConstructor
@Data
public class User implements Serializable{

    private String username;

}
