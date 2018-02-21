package com.epam.fluentConditionals.state;

import java.util.function.Supplier;

/**
 * Implementation of {@link State} decides what part of condition will be
 * executed according to boolean param
 * 
 * @author Raman_Skaskevich
 *
 */
public class ConditionState implements State
{
	private Supplier<Boolean> condition;

	public void setCondition(Supplier<Boolean> condition)
	{
		this.condition = condition;
	}

	@Override
	public void execute(StateContext context) {
		if(condition.get())
		{
			context.setState(context.getAvailableState(StateEnum.THEN));
		}
		else
		{
			context.setState(context.getAvailableState(StateEnum.ELSE));
		}			
	}
	
}