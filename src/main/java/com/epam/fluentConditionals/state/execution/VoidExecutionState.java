package com.epam.fluentConditionals.state.execution;

import com.epam.fluentConditionals.state.State;
import com.epam.fluentConditionals.state.StateContext;

/**
 * Implementation of {@link State}. Executes consumer function without params
 * and return values ( output purposes ).
 * 
 * @author Raman_Skaskevich
 *
 */
public class VoidExecutionState implements State{
	private Runnable action;
	@Override
	public void execute(StateContext context) {
		action.run();
		context.setState(null);
	}
	public VoidExecutionState(Runnable action) {
		super();
		this.action = action;
	}
}