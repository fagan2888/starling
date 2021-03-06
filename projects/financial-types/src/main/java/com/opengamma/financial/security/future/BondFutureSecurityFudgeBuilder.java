/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.future;

import java.util.ArrayList;
import java.util.List;

import org.fudgemsg.FudgeField;
import org.fudgemsg.FudgeMsg;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeBuilder;
import org.fudgemsg.mapping.FudgeBuilderFor;
import org.fudgemsg.mapping.FudgeDeserializer;
import org.fudgemsg.mapping.FudgeSerializer;

import com.opengamma.util.fudgemsg.AbstractFudgeBuilder;
import com.opengamma.util.time.ZonedDateTimeFudgeBuilder;

/**
 * A Fudge builder for {@code BondFutureSecurity}.
 */
@FudgeBuilderFor(BondFutureSecurity.class)
public class BondFutureSecurityFudgeBuilder extends AbstractFudgeBuilder implements FudgeBuilder<BondFutureSecurity> {

  /** Field name. */
  public static final String BASKET_FIELD_NAME = "basket";
  /** Field name. */
  public static final String BOND_TYPE_FIELD_NAME = "bondType";
  /** Field name. */
  public static final String FIRST_DELIVERY_DATE_FIELD_NAME = "firstDeliveryDate";
  /** Field name. */
  public static final String LAST_DELIVERY_DATE_FIELD_NAME = "lastDeliveryDate";

  @Override
  public MutableFudgeMsg buildMessage(final FudgeSerializer serializer, final BondFutureSecurity object) {
    final MutableFudgeMsg msg = serializer.newMessage();
    BondFutureSecurityFudgeBuilder.toFudgeMsg(serializer, object, msg);
    return msg;
  }

  public static void toFudgeMsg(final FudgeSerializer serializer, final BondFutureSecurity object, final MutableFudgeMsg msg) {
    FutureSecurityFudgeBuilder.toFudgeMsg(serializer, object, msg);
    if (object.getBasket() != null) {
      for (final BondFutureDeliverable bfd : object.getBasket()) {
        addToMessage(serializer, msg, BASKET_FIELD_NAME, bfd, BondFutureDeliverable.class);
      }
    }
    addToMessage(msg, FIRST_DELIVERY_DATE_FIELD_NAME, ZonedDateTimeFudgeBuilder.toFudgeMsg(serializer, object.getFirstDeliveryDate()));
    addToMessage(msg, LAST_DELIVERY_DATE_FIELD_NAME, ZonedDateTimeFudgeBuilder.toFudgeMsg(serializer, object.getLastDeliveryDate()));
  }

  @Override
  public BondFutureSecurity buildObject(final FudgeDeserializer deserializer, final FudgeMsg msg) {
    final BondFutureSecurity object = new BondFutureSecurity();
    BondFutureSecurityFudgeBuilder.fromFudgeMsg(deserializer, msg, object);
    return object;
  }

  public static void fromFudgeMsg(final FudgeDeserializer deserializer, final FudgeMsg msg, final BondFutureSecurity object) {
    FutureSecurityFudgeBuilder.fromFudgeMsg(deserializer, msg, object);
    final List<FudgeField> basketFields = msg.getAllByName(BASKET_FIELD_NAME);
    final List<BondFutureDeliverable> basket = new ArrayList<>(basketFields.size());
    for (final FudgeField field : basketFields) {
      basket.add(deserializer.fieldValueToObject(BondFutureDeliverable.class, field));
    }
    object.setBasket(basket);
    object.setFirstDeliveryDate(ZonedDateTimeFudgeBuilder.fromFudgeMsg(deserializer, msg.getMessage(FIRST_DELIVERY_DATE_FIELD_NAME)));
    object.setLastDeliveryDate(ZonedDateTimeFudgeBuilder.fromFudgeMsg(deserializer, msg.getMessage(LAST_DELIVERY_DATE_FIELD_NAME)));
  }

}
