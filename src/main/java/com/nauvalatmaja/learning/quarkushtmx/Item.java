package com.nauvalatmaja.learning.quarkushtmx;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Item {
	private int id;
	private boolean done;
	private String task;
}

