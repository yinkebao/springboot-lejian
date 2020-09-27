package com.tianque.grid.workdairy.ui.vo;

import com.tianque.grid.business.ui.vo.AttachmentFileVO;
import com.tianque.grid.ddd.ui.vo.Vo;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName GridCrewVo
 * @Description
 * @Author ykb
 * @Date 2020/2/11
 */
@Setter
@Getter
@NoArgsConstructor
public class GridCrewVo extends Vo {

  private Long id;

  private Long deptId;

  private String deptCode;

  private String name;

  private Integer gender;

  private Boolean ifGridLeader;

  private String idCard;

  private Date birthDay;

  private Long nation;

  private Long politicalStatus;

  private Long education;

  private Long maritalStatus;

  private String address;

  private String phone;

  private String landLine;

  private Boolean ifFullTimeStaff;

  private String workDate;

  private String position;

  private Long userId;

  private Integer sort;

  private String createUser;

  private Date createDate;

  private AttachmentFileVO attachmentFileVO;

}
