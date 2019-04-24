package org.sadr.share.main.layer;

import org.sadr._core.meta.generic.GenericService;

public interface LayerService extends GenericService<Layer> {

    public void uploadMap(String mapName);
}

