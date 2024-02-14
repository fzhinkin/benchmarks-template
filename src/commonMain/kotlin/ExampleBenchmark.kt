package org.example

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State

@State(Scope.Benchmark)
open class ExampleBenchmark {
    @Benchmark
    fun hello() = 42
}
