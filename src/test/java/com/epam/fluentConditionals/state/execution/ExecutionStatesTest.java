package com.epam.fluentConditionals.state.execution;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.epam.fluentConditionals.state.ConditionState;
import com.epam.fluentConditionals.state.StateContext;
import com.epam.fluentConditionals.state.execution.ConsumerExecutionState;
import com.epam.fluentConditionals.state.execution.FunctionExecutionState;
import com.epam.fluentConditionals.state.execution.SupplierExecutionState;
import com.epam.fluentConditionals.state.execution.VoidExecutionState;

/**
 * Test class for {@link ConsumerExecutionState},
 * {@link SupplierExecutionState}, {@link FunctionExecutionState},
 * {@link VoidExecutionState}
 * 
 * @author Raman_Skaskevich
 *
 */
public class ExecutionStatesTest {
	
	@Mock
	public StateContext context;
	
	@Mock
	public Consumer<String> consumer;
	
	@Mock
	public Supplier<String> supplier;
	
	@Mock
	public Runnable action;

	@Mock
	public Function<String, Integer> function;
	
	public ConditionState state;
	
	@Before
	public void doBefore()
	{
		state = new ConditionState();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testConsumerExStateExecuted()
	{
		ConsumerExecutionState<String> ces = new ConsumerExecutionState<String>(consumer);
		String given = "given";
		Mockito.doReturn(given).when(context).getGivenParameter();

		ces.execute(context);

		Mockito.verify(consumer).accept(given);
	}

	@Test
	public void testSupplierExStateExecuted()
	{
		SupplierExecutionState<String> ses = new SupplierExecutionState<String>(supplier);
		String given = "given";
		Mockito.doReturn(given).when(supplier).get();

		ses.execute(context);

		Mockito.verify(context).setReturnValue(given);
		Mockito.verify(supplier).get();
	}

	@Test
	public void testFuncExStateExecuted()
	{
		FunctionExecutionState<String, Integer> fes = new FunctionExecutionState<String, Integer>(function);
		Integer fR = 1;
		String arg = "arg";
		Mockito.doReturn(arg).when(context).getGivenParameter();
		Mockito.doReturn(fR).when(function).apply(arg);

		fes.execute(context);

		Mockito.verify(context).setReturnValue(fR);
		Mockito.verify(function).apply(arg);
	}

	@Test
	public void testVoidExStateExecuted()
	{
		VoidExecutionState ves = new VoidExecutionState(action);

		ves.execute(context);

		Mockito.verify(action).run();
	}
}
