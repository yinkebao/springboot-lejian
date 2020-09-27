package com.tianque.grid.workdairy.model.workdiary;

import com.tianque.grid.core.configuration.jpalistener.DataEntityListener;
import com.tianque.grid.ddd.domain.model.Entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName WorkDiary
 * @Description
 * @Author ykb
 * @Date 2020/2/13
 */
@Slf4j
@Data
@Table(name = "work_diary")
@javax.persistence.Entity
@EntityListeners(DataEntityListener.class)
public class WorkDiary extends Entity {

  @Column(name = "dept_id", columnDefinition = "int COMMENT '单位id'")
  private Long deptId;

  @Column(name = "dept_code", columnDefinition = "varchar(32) COMMENT '单位内部编码'")
  private String deptCode;

  @Column(name = "work_time", columnDefinition = "date COMMENT '工作时间'")
  private Date workTime;

  @Column(name = "work_place", columnDefinition = "varchar(50) COMMENT '工作地点'")
  private String workPlace;

  @Column(name = "diary_type", columnDefinition = "int COMMENT '日志类型'")
  private Long diaryType;

  @Column(name = "work_content", columnDefinition = "int COMMENT '工作内容'")
  private String workContent;

  @Column(name = "work_user_name", columnDefinition = "int COMMENT '工作人员'")
  private String workUserName;

}
