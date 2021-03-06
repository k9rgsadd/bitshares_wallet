package com.github.chouheiwa.wallet.socket.chain;

import com.github.chouheiwa.wallet.socket.fc.io.base_encoder;
import com.github.chouheiwa.wallet.socket.fc.io.raw_type;
import com.google.common.primitives.UnsignedInteger;

import java.util.HashSet;
import java.util.Set;

import static com.github.chouheiwa.wallet.socket.chain.config.*;

public class bitasset_options {
    public int feed_lifetime_sec = config.GRAPHENE_DEFAULT_PRICE_FEED_LIFETIME;
    //最小喂价
    public short minimum_feeds = 1;
    //强制清算延迟
    public int force_settlement_delay_sec = config.GRAPHENE_DEFAULT_FORCE_SETTLEMENT_DELAY;

    public short force_settlement_offset_percent = config.GRAPHENE_DEFAULT_FORCE_SETTLEMENT_OFFSET;

    public short maximum_force_settlement_volume = config.GRAPHENE_DEFAULT_FORCE_SETTLEMENT_MAX_VOLUME;

    public object_id<asset_object> short_backing_asset = object_id.create_from_string("1.3.0");

    public Set extensions = new HashSet();

    public void write_to_encoder(base_encoder baseEncoder) {
        raw_type raw_object = new raw_type();

        baseEncoder.write(raw_object.get_byte_array(feed_lifetime_sec));

        raw_object.pack(baseEncoder,UnsignedInteger.fromIntBits(minimum_feeds));

        baseEncoder.write(raw_object.get_byte_array(force_settlement_delay_sec));

        baseEncoder.write(raw_object.get_byte_array(force_settlement_offset_percent));
//        raw_object.pack(baseEncoder,UnsignedInteger.fromIntBits(force_settlement_offset_percent));

//        raw_object.pack(baseEncoder,UnsignedInteger.fromIntBits(maximum_force_settlement_volume));
        baseEncoder.write(raw_object.get_byte_array(maximum_force_settlement_volume));

        short_backing_asset.write_to_encoder(baseEncoder);

        raw_object.pack(baseEncoder,UnsignedInteger.fromIntBits(extensions.size()));
    }
}
