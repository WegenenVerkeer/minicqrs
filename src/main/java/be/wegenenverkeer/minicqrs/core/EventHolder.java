package be.wegenenverkeer.minicqrs.core;

import java.time.Instant;

public record EventHolder<ID, E>(ID id, E event, long sequence, Instant occured) {

}
