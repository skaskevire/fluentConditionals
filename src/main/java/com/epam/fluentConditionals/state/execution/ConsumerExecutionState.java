package com.epam.fluentConditionals.state.execution;

import java.util.function.Consumer;

import com.epam.fluentConditionals.state.State;
import com.epam.fluentConditionals.state.StateContext;

/**
 * Implementation of {@link State}. Executes consumer function.
 * 
 * @author Raman_Skaskevich
 *
 */
public class ConsumerExecutionState<T> implements State {
	private Consumer<T> consumer;
	
	public ConsumerExecutionState(Consumer<T> consumer) {
		super();
		this.consumer = consumer;
	}

	@Override
	public void execute(StateContext context) {
		consumer.accept(context.getGivenParameter());
		context.setState(null);
	}
}
