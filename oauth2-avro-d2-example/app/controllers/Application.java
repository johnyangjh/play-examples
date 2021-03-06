/**
 * Copyright 2014 Thomas Feng
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import controllers.protocols.Example;
import controllers.protocols.ExampleClient;
import me.tfeng.play.avro.BinaryIpcController;
import me.tfeng.play.plugins.AvroD2Plugin;
import play.libs.F.Promise;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

/**
 * @author Thomas Feng (huining.feng@gmail.com)
 */
@Service
public class Application extends Controller {

  @PreAuthorize("hasRole('ROLE_USER')")
  public Promise<Result> invoke(String message) throws Exception {
    ExampleClient proxy = AvroD2Plugin.client(ExampleClient.class);
    return proxy.echo(message).map(response -> Results.ok(response.toString()));
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @BodyParser.Of(BodyParser.Raw.class)
  public Promise<Result> postAvroBinary() throws Throwable {
    return BinaryIpcController.post(Example.class.getName());
  }
}
