/*
 * Copyright 2014 Goodow.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.goodow.realtime.store.server;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;

import com.alienos.guice.VertxModule;
import com.goodow.realtime.channel.server.impl.VertxPlatform;
import com.goodow.realtime.operation.Transformer;
import com.goodow.realtime.operation.impl.CollaborativeOperation;
import com.goodow.realtime.operation.impl.CollaborativeTransformer;
import com.goodow.realtime.store.server.impl.MemoryDeltaStorage;
import com.goodow.realtime.store.server.persistence.RedisElasticSearchStorage;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Container;

import java.util.Map;

import javax.inject.Provider;

public class StoreModule extends AbstractModule implements VertxModule {
  public static final long REPLY_TIMEOUT = 15 * 1000;
  private Vertx vertx;
  private Container container;

  @Override
  public void setContainer(Container container) {
    this.container = container;
  }

  @Override
  public void setVertx(Vertx vertx) {
    this.vertx = vertx;
  }

  @Override
  protected void configure() {
    VertxPlatform.register(vertx);

    bind(new TypeLiteral<Transformer<CollaborativeOperation>>() {
    }).to(CollaborativeTransformer.class);

    MapBinder<String, DeltaStorage> storages =
        MapBinder.newMapBinder(binder(), String.class, DeltaStorage.class);
    storages.addBinding("memory").to(MemoryDeltaStorage.class);
    storages.addBinding("redis-elasticsearch").to(RedisElasticSearchStorage.class);
  }

  @Provides
  @Singleton
  DeltaStorage provideMutationStorage (Map<String, Provider<DeltaStorage>> storages) {
    String storage = container.config().getObject("realtime_store", new JsonObject())
            .getString("storage", "memory");
    return storages.get(storage).get();
  }
}