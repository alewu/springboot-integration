package com.ale.aop;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author alewu
 * @date 2020/6/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IronDoor extends BaseDoor {
    private String materials;
}
