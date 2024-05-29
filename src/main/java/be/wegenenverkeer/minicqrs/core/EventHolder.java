package be.wegenenverkeer.minicqrs.core;

import java.time.Instant;

public record EventHolder<E>(String id, E event, long sequence, Instant occured) {

}
