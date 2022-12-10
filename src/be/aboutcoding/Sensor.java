package be.aboutcoding;

/**
 * This interface represents an abstraction. It tells us what is needed or wanted. How to get to what is needed or what
 * is wanted, is omitted. But even though there is no specific implementation, we still have some understanding of what
 * needs to happen. Therefore, the exact implementation can be seen as a detail.
 */
public interface Sensor {
    boolean hasValidFirmwareVersion();
}
