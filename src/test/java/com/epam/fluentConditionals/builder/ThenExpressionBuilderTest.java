package com.epam.fluentConditionals.builder;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.epam.fluentConditionals.TestHelper;
import com.epam.fluentConditionals.builder.ElseExpressionBuilder;
import com.epam.fluentConditionals.builder.ThenExpressionBuilder;
import com.epam.fluentConditionals.state.State;
import com.epam.fluentConditionals.state.StateContext;
import com.epam.fluentConditionals.state.StateEnum;

/**
 * Test class for {@link ThenExpressionBuilder}
 * 
 * @author Raman_Skaskevich
 *
 */
public class ThenExpressionBuilderTest {
	@Mock
	StateContext stateContext;

	ThenExpressionBuilder thenExpressionBuilder;

	@Before
	public void doBefore() {
		MockitoAnnotations.initMocks(this);
		thenExpressionBuilder = new ThenExpressionBuilder(stateContext);
	}

	@Test
	public void testBuildExecutionStateWithNumberParam() {
		Integer number = 1;
		ElseExpressionBuilder result = thenExpressionBuilder.thenReturn(number);

		Assert.assertNotNull(result);
		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.THEN), Mockito.any(State.class));
	}

	@Test
	public void testBuildExecutionStateWithSupplierParam() {
		Supplier<Integer> supplier = TestHelper::getLowNumber;
		ElseExpressionBuilder result = thenExpressionBuilder.thenReturn(supplier);

		Assert.assertNotNull(result);
		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.THEN), Mockito.any(State.class));
	}

	@Test
	public void testBuildExecutionStateWithVoidParam() {
		Runnable action = TestHelper::printFoo;
		ElseExpressionBuilder result = thenExpressionBuilder.then(action);

		Assert.assertNotNull(result);
		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.THEN), Mockito.any(State.class));
	}

	@Test
	public void testBuildExecutionStateWithConsumerParam() {
		Consumer<String> consumer = TestHelper::printFirstChar;
		ElseExpressionBuilder result = thenExpressionBuilder.then(consumer);

		Assert.assertNotNull(result);
		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.THEN), Mockito.any(State.class));
	}

	@Test
	public void testBuildExecutionStateWithFunctionParam() {
		Function<String, Integer> f = String::length;
		ElseExpressionBuilder result = thenExpressionBuilder.thenReturn(f);

		Assert.assertNotNull(result);
		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.THEN), Mockito.any(State.class));
	}

	@Test
	public void testBuildExecutionStateWithRuntimeExParam() {
		Supplier<RuntimeException> supplier = RuntimeException::new;
		String message = "message";

		thenExpressionBuilder.thenThrow(supplier, message);

		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.THEN), Mockito.any(State.class));
	}
}
