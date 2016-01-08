/**
 * Copyright (C) 2010-2015 eBusiness Information, Excilys Group
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
package org.androidannotations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Injects an {@link EApplication} annotated class in an enhanced class.
 * </p>
 * <p>
 * Please check that your registered the application in the AndroidManifest.xml
 * class prior to using this annotation.
 * </p>
 * <blockquote>
 * 
 * Example :
 * 
 * <pre>
 * &#064;EApplication
 * public class MyApplication extends Application {
 * }
 * 
 * &#064;EBean
 * public class MyBean {
 * 
 * 	&#064;App
 * 	MyApplication myApp;
 * 
 * 	&#064;App
 * 	void methodInjection(MyApplication app) {
 * 		// do stuff
 * 	}
 * 
 * }
 * </pre>
 * 
 * </blockquote>
 * 
 * @see EApplication
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
public @interface App {
}
