package com.epam.fluentConditionals;

import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

import com.epam.fluentConditionals.FluentConditionals;
import com.epam.fluentConditionals.builder.ThenExpressionBuilder;
import com.epam.fluentConditionals.builder.WhenExpressionBuilder;

public class FluentConditionalsTest {

	@Test
	public void testBaseExpressionBuilderCreatedWithGivenParam() {
		String param = "Greetings";

		WhenExpressionBuilder result = FluentConditionals.given(param);

		Assert.assertNotNull(result);
		Assert.assertEquals(param, result.getContext().getGivenParameter());
	}

	@Test
	public void testBaseExpressionBuilderCreatedWithGivenSupplierParam() {
		Supplier<String> supplier = TestHelper::getAString;

		WhenExpressionBuilder result = FluentConditionals.given(supplier);

		Assert.assertNotNull(result);
		Assert.assertEquals(supplier.get(), result.getContext().getGivenParameter());
	}
	
	@Test
	public void testThenExpressionBuilderCreatedWithGivenSupplierParam() {
		Supplier<Boolean> supplier = TestHelper::somethingIsTrue;

		ThenExpressionBuilder result = FluentConditionals.when(supplier);

		Assert.assertNotNull(result);
	}

	@Test
	public void testThenExpressionBuilderCreatedWithGivenBooleanParam() {
		Boolean param = true;

		ThenExpressionBuilder result = FluentConditionals.when(param);

		Assert.assertNotNull(result);
	}
}
