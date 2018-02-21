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
import com.epam.fluentConditionals.EnhancedExceptions.CustomException;
import com.epam.fluentConditionals.builder.ElseExpressionBuilder;
import com.epam.fluentConditionals.state.State;
import com.epam.fluentConditionals.state.StateContext;
import com.epam.fluentConditionals.state.StateEnum;

/**
 * Test class for {@link ElseExpressionBuilder}
 * 
 * @author Raman_Skaskevich
 *
 */
public class ElseExpressionBuilderTest {
	@Mock
	StateContext stateContext;
	
	ElseExpressionBuilder elseExpressionBuilder;
	
	@Before
	public void doBefore() {
		MockitoAnnotations.initMocks(this);
		elseExpressionBuilder = new ElseExpressionBuilder(stateContext);
	}

	@Test
	public void testBuildExecutionStateWithNumberParam() {
		Integer number = 1;
		elseExpressionBuilder.orElse(number);

		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.ELSE), Mockito.any(State.class));
	}

	@Test
	public void testBuildExecutionStateWithSupplierParam() {
		Supplier<Integer> supplier = TestHelper::getLowNumber;
		elseExpressionBuilder.orElse(supplier);

		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.ELSE), Mockito.any(State.class));
	}

	@Test
	public void testBuildExecutionStateWithVoidParam() {
		Runnable action = TestHelper::printFoo;
		elseExpressionBuilder.orElse(action);

		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.ELSE), Mockito.any(State.class));
	}

	@Test
	public void testBuildExecutionStateWithConsumerParam() {
		Consumer<String> consumer = TestHelper::printFirstChar;
		elseExpressionBuilder.orElse(consumer);

		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.ELSE), Mockito.any(State.class));
	}

	@Test
	public void testBuildExecutionStateWithFunctionParam() {
		Function<String, Integer> f = String::length;
		elseExpressionBuilder.orElse(f);

		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.ELSE), Mockito.any(State.class));
	}

	@Test
	public void testBuildExecutionStateWithSupplierRuntimeExParam() {
		Supplier<RuntimeException> supplier = RuntimeException::new;

		elseExpressionBuilder.orElseThrow(supplier);

		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.ELSE), Mockito.any(State.class));
	}

	@Test
	public void testBuildExecutionStateWithRuntimeExParam() {
		RuntimeException ex = new RuntimeException();

		elseExpressionBuilder.orElseThrowE(ex);

		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.ELSE), Mockito.any(State.class));
	}
	
	@Test( expected = CustomException.class)
	public void testBuildExecutionStateWithCustomExParam() throws CustomException {
		Supplier<CustomException> supplier = CustomException::new;
		Mockito.doReturn(supplier.get()).when(stateContext).getReturnValue();
		elseExpressionBuilder.orElseThrowEx(supplier);
	}

	@Test
	public void testBuildExecutionStateWithErrorParam() {
		Supplier<Throwable> supplier = Error::new;
		String message = "message";
		elseExpressionBuilder.orElseThrow(supplier, message);

		Mockito.verify(stateContext).addAvailableState(Mockito.eq(StateEnum.ELSE), Mockito.any(State.class));
	}
}
