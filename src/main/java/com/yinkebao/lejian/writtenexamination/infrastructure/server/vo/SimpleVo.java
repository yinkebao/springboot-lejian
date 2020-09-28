package com.yinkebao.lejian.writtenexamination.infrastructure.server.vo;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * @ClassName SimpleVo
 * @Description
 * @Author ykb
 * @Date 2020/9/28
 */
@ApiModel("单字段返回对象")
public class SimpleVo<T> implements Serializable {
	T field;

	public T getField() {
		return this.field;
	}

	public void setField(T field) {
		this.field = field;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof SimpleVo)) {
			return false;
		} else {
			SimpleVo<?> other = (SimpleVo)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$field = this.getField();
				Object other$field = other.getField();
				if (this$field == null) {
					if (other$field != null) {
						return false;
					}
				} else if (!this$field.equals(other$field)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(Object other) {
		return other instanceof SimpleVo;
	}

	@Override
	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		Object $field = this.getField();
		return result * 59 + ($field == null ? 43 : $field.hashCode());
	}

	@Override
	public String toString() {
		return "SimpleVo(field=" + this.getField() + ")";
	}

	public SimpleVo(T field) {
		this.field = field;
	}

	public SimpleVo() {
	}
}
