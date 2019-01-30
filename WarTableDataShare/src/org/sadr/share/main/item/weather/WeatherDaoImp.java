package org.sadr.share.main.item.weather;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class WeatherDaoImp extends GenericDaoImpl<Weather> implements WeatherDao {
}
