package com.epam.fluentConditionals.builder;

import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.epam.fluentConditionals.TestHelper;
import com.epam.fluentConditionals.builder.ThenExpressionBuilder;
import com.epam.fluentConditionals.builder.WhenExpressionBuilder;
import com.epam.fluentConditionals.state.ConditionState;
import com.epam.fluentConditionals.state.StateContext;

/**
 * Test class for {@link BaseExpressionBuilder}
 * 
 * @author Raman_Skaskevich
 *
 */
public class BaseExpressionBuilderTest {
	
	@Mock
	StateContext stateContext;
	
	@Before
	public void doBefore()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenConditionStateCreatedProperlyBooleanParam()
	{
		WhenExpressionBuilder beb = new WhenExpressionBuilder(stateContext);
		Boolean condition = true;
		ThenExpressionBuilder result = beb.when(condition);
		
		Assert.assertNotNull(result);
		Mockito.verify(stateContext).setState(Mockito.any(ConditionState.class));
	}

	@Test
	public void testWhenConditionStateCreatedProperlySupplierParam()
	{
		WhenExpressionBuilder beb = new WhenExpressionBuilder(stateContext);
		Supplier<Boolean> condition = TestHelper::somethingIsTrue;
		ThenExpressionBuilder result = beb.when(condition);
		
		Assert.assertNotNull(result);
		Mockito.verify(stateContext).setState(Mockito.any(ConditionState.class));
	}
}
