/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.solr.core;

import org.apache.solr.common.util.NamedList;
import org.apache.solr.schema.IndexSchema;

import java.util.function.Function;

/**
 * Stores a core's configuration in the form of a SolrConfig and IndexSchema.
 * Immutable.
 * @see ConfigSetService
 */
public class ConfigSet {

  private final String name;

  private final SolrConfig solrconfig;

  /**Provide a Schema object on demand
   * The first Boolean is to signify a a forcefetch
   */
  private final Function<Boolean, IndexSchema> indexSchema;

  @SuppressWarnings({"rawtypes"})
  private final NamedList properties;

  private final boolean trusted;

  @SuppressWarnings({"rawtypes"})
  public ConfigSet(String name, SolrConfig solrConfig, Function<Boolean, IndexSchema> indexSchema,
      NamedList properties, boolean trusted) {
    this.name = name;
    this.solrconfig = solrConfig;
    this.indexSchema = indexSchema;
    this.properties = properties;
    this.trusted = trusted;
  }

  public String getName() {
    return name;
  }

  public SolrConfig getSolrConfig() {
    return solrconfig;
  }

  /**
   *
   * @param forceFetch get a fresh value and not cached valiue
   */
  public IndexSchema getIndexSchema(boolean forceFetch) {
    return indexSchema.apply(forceFetch);
  }
  public IndexSchema getIndexSchema() {
    return indexSchema.apply(Boolean.FALSE);
  }

  @SuppressWarnings({"rawtypes"})
  public NamedList getProperties() {
    return properties;
  }
  
  public boolean isTrusted() {
    return trusted;
  }
}
