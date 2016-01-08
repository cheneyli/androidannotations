/**
 * Copyright (C) 2010-2013 eBusiness Information, Excilys Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.androidannotations.holder;

import static com.sun.codemodel.JExpr._new;
import static com.sun.codemodel.JExpr._this;
import static com.sun.codemodel.JMod.PRIVATE;
import static com.sun.codemodel.JMod.PUBLIC;

import static com.sun.codemodel.JExpr.FALSE;
import static com.sun.codemodel.JExpr.TRUE;
import static com.sun.codemodel.JExpr._new;
import static com.sun.codemodel.JExpr._null;
import static com.sun.codemodel.JExpr._super;
import static com.sun.codemodel.JExpr._this;
import static com.sun.codemodel.JExpr.cast;
import static com.sun.codemodel.JExpr.invoke;
import static com.sun.codemodel.JMod.PRIVATE;
import static com.sun.codemodel.JMod.PUBLIC;
import javax.lang.model.element.TypeElement;

import com.sun.codemodel.*;
import org.androidannotations.api.mvc.MVCAdapter;
import org.androidannotations.helper.AndroidManifest;
import org.androidannotations.helper.IntentBuilder;
import org.androidannotations.helper.ServiceIntentBuilder;
import org.androidannotations.process.ProcessHolder;

public class EServiceHolder extends EComponentHolder implements HasIntentBuilder {

    private ServiceIntentBuilder intentBuilder;
	private JDefinedClass intentBuilderClass;
	private JFieldVar intentField;
    private JMethod onDestroy;
	public EServiceHolder(ProcessHolder processHolder, TypeElement annotatedElement, AndroidManifest androidManifest) throws Exception {
		super(processHolder, annotatedElement);
        intentBuilder = new ServiceIntentBuilder(this, androidManifest);
        intentBuilder.build();
	}

    @Override
    public IntentBuilder getIntentBuilder() {
        return intentBuilder;
    }

    @Override
    public JMethod getOnDestroy() {
        if(onDestroy==null){
            onDestroy = generatedClass.method(PUBLIC, codeModel().VOID, "onDestroy");
            onDestroy.annotate(Override.class);
            JBlock onDestroyBody = onDestroy.body();
            onDestroyBody.invoke(_super(), onDestroy);

        }
        return onDestroy;
    }

    @Override
    public JExpression getNewMvcAdapter() {
        return _new(refClass(MVCAdapter.class)).arg(_this());
    }
    @Override
    public boolean needMvcAdapter() {
        return true;
    }

    @Override
	protected void setContextRef() {
		contextRef = _this();
	}

	@Override
	protected void setInit() {
		init = generatedClass.method(PRIVATE, codeModel().VOID, "init_");
		createOnCreate();
	}

	private void createOnCreate() {
		JMethod onCreate = generatedClass.method(PUBLIC, codeModel().VOID, "onCreate");
		onCreate.annotate(Override.class);
		JBlock onCreateBody = onCreate.body();
		onCreateBody.invoke(getInit());
		onCreateBody.invoke(JExpr._super(), onCreate);
	}

	@Override
	public void setIntentBuilderClass(JDefinedClass intentBuilderClass) {
		this.intentBuilderClass = intentBuilderClass;
	}

	@Override
	public JDefinedClass getIntentBuilderClass() {
		return intentBuilderClass;
	}

	@Override
	public void setIntentField(JFieldVar intentField) {
		this.intentField = intentField;
	}

	@Override
	public JFieldVar getIntentField() {
		return intentField;
	}
}
