package org.zerock.ex00.domain;

import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
public class AttachVO {
    
    private Long ano;
    private Long bno;
    private String uuid;
    private String fileName;
}
