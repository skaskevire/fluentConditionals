package com.epam.fluentConditionals.builder;

import com.epam.fluentConditionals.state.StateContext;

/**
 * Builds whole condition;
 * 
 * @author Raman_Skaskevich
 *
 */
public class EndingExpressionBuilder {
	public StateContext context;

	public EndingExpressionBuilder(StateContext context) {
		super();
		this.context = context;
	}

	public <T> T buildAndReturn()
	{
		build();
		return context.getReturnValue();
	}
	
	public <T extends Throwable> void build() throws T
	{
		while(context.getState() != null)
		{
			context.execute();
		}
	}
	
	public StateContext getContext() {
		return context;
	}

	public void setContext(StateContext context) {
		this.context = context;
	}
}
