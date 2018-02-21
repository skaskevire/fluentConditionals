package com.epam.fluentConditionals.builder;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.epam.fluentConditionals.EnhancedExceptions.CustomException;
import com.epam.fluentConditionals.state.State;
import com.epam.fluentConditionals.state.StateContext;
import com.epam.fluentConditionals.state.StateEnum;
import com.epam.fluentConditionals.state.execution.ConsumerExecutionState;
import com.epam.fluentConditionals.state.execution.FunctionExecutionState;
import com.epam.fluentConditionals.state.execution.SupplierExecutionState;
import com.epam.fluentConditionals.state.execution.VoidExecutionState;

/**
 * Builds else part of condition;
 * 
 * @author Raman_Skaskevich
 *
 */
public class ElseExpressionBuilder extends EndingExpressionBuilder{
	public ElseExpressionBuilder(StateContext context) {
		super(context);
	}
	
	public <T> T orElseThrowE(RuntimeException e) {
		addStateToContext(new VoidExecutionState(() -> {throw e;}));
		return buildAndReturn();		
	}

	public void orElse(Runnable action)
	{
		addStateToContext(new VoidExecutionState(action));
		build();
	}
	
	public <T> T orElse(T t) {
		return orElse(() -> t);
	}
	
	public <T> T orElse(Supplier<T> supplier) {
		addStateToContext(new SupplierExecutionState<>(supplier));
		return buildAndReturn();
	}
	
	public <T> void orElse(Consumer<T> consumer) {
		addStateToContext(new ConsumerExecutionState<>(consumer));
		build();
	}
	public <I, O> O orElse(Function<I, O> f)
	{
		addStateToContext(new FunctionExecutionState<I, O>(f));
		return buildAndReturn();
	}
	public <T extends Throwable> void orElseThrowEx(Supplier<T> action) throws T{
		addStateToContext(new SupplierExecutionState<>(action));
		T t = buildAndReturn();
		throw t;
	}
	
	public void orElseThrow(Supplier<Throwable> action, String message) {
		addStateToContext(new SupplierExecutionState<Throwable>(() -> {throw new RuntimeException(message, action.get());}));
		build();
	}
	
	public <T> T orElseThrow(Supplier<RuntimeException> action) {
		addStateToContext(new VoidExecutionState(() -> {throw action.get();}));
		return buildAndReturn();
	}
	
	private void addStateToContext(State state)
	{
		context.addAvailableState(StateEnum.ELSE, state);
	}
}