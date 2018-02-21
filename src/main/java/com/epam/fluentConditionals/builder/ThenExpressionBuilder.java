package com.epam.fluentConditionals.builder;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.epam.fluentConditionals.state.State;
import com.epam.fluentConditionals.state.StateContext;
import com.epam.fluentConditionals.state.StateEnum;
import com.epam.fluentConditionals.state.execution.ConsumerExecutionState;
import com.epam.fluentConditionals.state.execution.FunctionExecutionState;
import com.epam.fluentConditionals.state.execution.SupplierExecutionState;
import com.epam.fluentConditionals.state.execution.VoidExecutionState;

/**
 * Builds then part of condition;
 * 
 * @author Raman_Skaskevich
 *
 */
public class ThenExpressionBuilder extends EndingExpressionBuilder {
	private ElseExpressionBuilder elseExpressionBuilder;

	public ThenExpressionBuilder(StateContext context) {
		super(context);
		elseExpressionBuilder = new ElseExpressionBuilder(context);
	}

	public <T> ElseExpressionBuilder thenReturn(T number) {
		return thenReturn(() -> number);
	}

	public <T> ElseExpressionBuilder thenReturn(Supplier<T> supplier) {
		addStateToContext(new SupplierExecutionState<>(supplier));
		return elseExpressionBuilder;
	}

	public ElseExpressionBuilder then(Runnable action) {
		addStateToContext(new VoidExecutionState(action));
		return elseExpressionBuilder;
	}

	public <T> ElseExpressionBuilder then(Consumer<T> consumer) {
		addStateToContext(new ConsumerExecutionState<>(consumer));
		return elseExpressionBuilder;
	}

	public <I, O> ElseExpressionBuilder thenReturn(Function<I, O> f) {
		addStateToContext(new FunctionExecutionState<>(f));
		return elseExpressionBuilder;
	}

	public void thenThrow(Supplier<RuntimeException> action, String message) {
		addStateToContext(new VoidExecutionState(() -> {
			throw new RuntimeException(message, action.get());
		}));
		build();
	}

	private void addStateToContext(State state) {
		context.addAvailableState(StateEnum.THEN, state);
	}
}