package com.tianque.grid.workdairy.ui.vo;

import com.tianque.grid.business.ui.vo.AttachmentFileVO;
import com.tianque.grid.ddd.ui.vo.Vo;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName PrefectureInfoVo
 * @Description
 * @Author ykb
 * @Date 2020/2/7
 */
@Setter
@Getter
@NoArgsConstructor
public class PrefectureInfoVo extends Vo {

  private Long id;

  private Long deptId;

  private String deptCode;

  private Integer prefectureLevel;

  private String introduction;

  private Double area;

  private Integer doorsNum;

  private Integer populationNum;

  private Integer partyMemberNum;

  private Integer representativeNum;

  private Integer gridNum;

  private String districtLeader;

  private String secretary;

  private String secretaryPhone;

  private String director;

  private String directorPhone;

  private String createUser;

  private Date createDate;

  private AttachmentFileVO attachmentFileVO;

}
