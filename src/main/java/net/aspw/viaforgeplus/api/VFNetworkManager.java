package net.aspw.viaforgeplus.api;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;

public interface VFNetworkManager {

    ProtocolVersion viaForge$getTrackedVersion();

    void viaForge$setTrackedVersion(final ProtocolVersion version);
}