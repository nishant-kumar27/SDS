package com.test.entities;

import java.math.BigDecimal;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.test.entities.OrderDetail.OrderStatusEnum;

import rispl.dkart.order.lookup.dao.OrderStatus;

public class OrderDetailListener {

	@PrePersist
	void onPrePersist(OrderDetail orderDetail) {
	}

	@PostPersist
	void onPostPersist(OrderDetail orderDetail) {
	}

	@PostLoad
	void onPostLoad(OrderDetail orderDetail) {
		orderDetail.setStatusEnum(OrderStatusEnum.UNKNOWN);
		orderDetail.setStatus(OrderStatus.UNKNOWN);
		
		if (orderDetail.getOrdTy().equals("25")) {
			orderDetail.setStatusEnum(OrderStatusEnum.CANCELLED);
			orderDetail.setStatus(OrderStatus.CANCELLED);
		}
		else if (orderDetail.getScOrd() != null && orderDetail.getScTran() != null && orderDetail.getOrdTy() != null) {
			if (orderDetail.getOrdTy().equals("2") && orderDetail.getScOrd() != null) {
				orderDetail.setStatusEnum(OrderStatusEnum.RETURNED);
				orderDetail.setStatus(OrderStatus.RETURNED);
			} else if (orderDetail.getOrdTy().equals("18") && orderDetail.getScOrd() != null) {
				orderDetail.setStatusEnum(OrderStatusEnum.PAYMENT);
				orderDetail.setStatus(OrderStatus.PAYMENT);
			} else if (orderDetail.getScOrd().compareTo(new BigDecimal(0)) <= 0 && orderDetail.getScTran().compareTo(new BigDecimal(2)) == 0) {
				orderDetail.setStatusEnum(OrderStatusEnum.NEW);
				orderDetail.setStatus(OrderStatus.NEW);
			} else if (orderDetail.getScOrd().compareTo(new BigDecimal(1)) <= 0 && orderDetail.getScTran().compareTo(new BigDecimal(2)) == 0) {
				orderDetail.setStatusEnum(OrderStatusEnum.OPEN);
				orderDetail.setStatus(OrderStatus.OPEN);
			} else if (orderDetail.getScOrd().compareTo(new BigDecimal(3)) <= 0 && orderDetail.getScTran().compareTo(new BigDecimal(2)) == 0) {
				orderDetail.setStatusEnum(OrderStatusEnum.IN_PROGRESS);
				orderDetail.setStatus(OrderStatus.IN_PROGRESS);
			} else if (orderDetail.getScOrd().compareTo(new BigDecimal(5)) <= 0 && orderDetail.getScTran().compareTo(new BigDecimal(2)) == 0) {
				orderDetail.setStatusEnum(OrderStatusEnum.COMPELETED);
				orderDetail.setStatus(OrderStatus.COMPELETED);
			} else if (orderDetail.getScOrd().compareTo(new BigDecimal(7)) <= 0 && orderDetail.getScTran().compareTo(new BigDecimal(2)) == 0) {
				orderDetail.setStatusEnum(OrderStatusEnum.DELIVERED);
				orderDetail.setStatus(OrderStatus.DELIVERED);
			} else if (orderDetail.getScOrd().compareTo(new BigDecimal(0)) <= 0 && orderDetail.getScTran().compareTo(new BigDecimal(4)) == 0) {
				orderDetail.setStatusEnum(OrderStatusEnum.QUOTE);
				orderDetail.setStatus(OrderStatus.QUOTE);
			} else {
				orderDetail.setStatusEnum(OrderStatusEnum.UNKNOWN);
				orderDetail.setStatus(OrderStatus.UNKNOWN);
			}
		}
	}

	@PreUpdate
	void onPreUpdate(OrderDetail orderDetail) {
	}

	@PostUpdate
	void onPostUpdate(OrderDetail orderDetail) {
	}

	@PreRemove
	void onPreRemove(OrderDetail orderDetail) {
	}

	@PostRemove
	void onPostRemove(OrderDetail orderDetail) {
	}
}
