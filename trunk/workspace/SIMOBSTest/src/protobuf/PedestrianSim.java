// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pedestrianSim.proto

package protobuf;

public final class PedestrianSim {
  private PedestrianSim() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ObservationMessageOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // required string pedestrianId = 1;
    boolean hasPedestrianId();
    String getPedestrianId();
    
    // required double locationX = 2;
    boolean hasLocationX();
    double getLocationX();
    
    // required double locationY = 3;
    boolean hasLocationY();
    double getLocationY();
  }
  public static final class ObservationMessage extends
      com.google.protobuf.GeneratedMessage
      implements ObservationMessageOrBuilder {
    // Use ObservationMessage.newBuilder() to construct.
    private ObservationMessage(Builder builder) {
      super(builder);
    }
    private ObservationMessage(boolean noInit) {}
    
    private static final ObservationMessage defaultInstance;
    public static ObservationMessage getDefaultInstance() {
      return defaultInstance;
    }
    
    public ObservationMessage getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return protobuf.PedestrianSim.internal_static_protobuf_ObservationMessage_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protobuf.PedestrianSim.internal_static_protobuf_ObservationMessage_fieldAccessorTable;
    }
    
    private int bitField0_;
    // required string pedestrianId = 1;
    public static final int PEDESTRIANID_FIELD_NUMBER = 1;
    private Object pedestrianId_;
    public boolean hasPedestrianId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public String getPedestrianId() {
      Object ref = pedestrianId_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          pedestrianId_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getPedestrianIdBytes() {
      Object ref = pedestrianId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        pedestrianId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // required double locationX = 2;
    public static final int LOCATIONX_FIELD_NUMBER = 2;
    private double locationX_;
    public boolean hasLocationX() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public double getLocationX() {
      return locationX_;
    }
    
    // required double locationY = 3;
    public static final int LOCATIONY_FIELD_NUMBER = 3;
    private double locationY_;
    public boolean hasLocationY() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public double getLocationY() {
      return locationY_;
    }
    
    private void initFields() {
      pedestrianId_ = "";
      locationX_ = 0D;
      locationY_ = 0D;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      if (!hasPedestrianId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasLocationX()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasLocationY()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getPedestrianIdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeDouble(2, locationX_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeDouble(3, locationY_);
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getPedestrianIdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeDoubleSize(2, locationX_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeDoubleSize(3, locationY_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    @java.lang.Override
    protected Object writeReplace() throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static protobuf.PedestrianSim.ObservationMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static protobuf.PedestrianSim.ObservationMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.PedestrianSim.ObservationMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static protobuf.PedestrianSim.ObservationMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.PedestrianSim.ObservationMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static protobuf.PedestrianSim.ObservationMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.PedestrianSim.ObservationMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static protobuf.PedestrianSim.ObservationMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static protobuf.PedestrianSim.ObservationMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static protobuf.PedestrianSim.ObservationMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(protobuf.PedestrianSim.ObservationMessage prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements protobuf.PedestrianSim.ObservationMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return protobuf.PedestrianSim.internal_static_protobuf_ObservationMessage_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return protobuf.PedestrianSim.internal_static_protobuf_ObservationMessage_fieldAccessorTable;
      }
      
      // Construct using protobuf.PedestrianSim.ObservationMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        pedestrianId_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        locationX_ = 0D;
        bitField0_ = (bitField0_ & ~0x00000002);
        locationY_ = 0D;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protobuf.PedestrianSim.ObservationMessage.getDescriptor();
      }
      
      public protobuf.PedestrianSim.ObservationMessage getDefaultInstanceForType() {
        return protobuf.PedestrianSim.ObservationMessage.getDefaultInstance();
      }
      
      public protobuf.PedestrianSim.ObservationMessage build() {
        protobuf.PedestrianSim.ObservationMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private protobuf.PedestrianSim.ObservationMessage buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        protobuf.PedestrianSim.ObservationMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public protobuf.PedestrianSim.ObservationMessage buildPartial() {
        protobuf.PedestrianSim.ObservationMessage result = new protobuf.PedestrianSim.ObservationMessage(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.pedestrianId_ = pedestrianId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.locationX_ = locationX_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.locationY_ = locationY_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof protobuf.PedestrianSim.ObservationMessage) {
          return mergeFrom((protobuf.PedestrianSim.ObservationMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(protobuf.PedestrianSim.ObservationMessage other) {
        if (other == protobuf.PedestrianSim.ObservationMessage.getDefaultInstance()) return this;
        if (other.hasPedestrianId()) {
          setPedestrianId(other.getPedestrianId());
        }
        if (other.hasLocationX()) {
          setLocationX(other.getLocationX());
        }
        if (other.hasLocationY()) {
          setLocationY(other.getLocationY());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasPedestrianId()) {
          
          return false;
        }
        if (!hasLocationX()) {
          
          return false;
        }
        if (!hasLocationY()) {
          
          return false;
        }
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              pedestrianId_ = input.readBytes();
              break;
            }
            case 17: {
              bitField0_ |= 0x00000002;
              locationX_ = input.readDouble();
              break;
            }
            case 25: {
              bitField0_ |= 0x00000004;
              locationY_ = input.readDouble();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // required string pedestrianId = 1;
      private Object pedestrianId_ = "";
      public boolean hasPedestrianId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public String getPedestrianId() {
        Object ref = pedestrianId_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          pedestrianId_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setPedestrianId(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        pedestrianId_ = value;
        onChanged();
        return this;
      }
      public Builder clearPedestrianId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        pedestrianId_ = getDefaultInstance().getPedestrianId();
        onChanged();
        return this;
      }
      void setPedestrianId(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000001;
        pedestrianId_ = value;
        onChanged();
      }
      
      // required double locationX = 2;
      private double locationX_ ;
      public boolean hasLocationX() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public double getLocationX() {
        return locationX_;
      }
      public Builder setLocationX(double value) {
        bitField0_ |= 0x00000002;
        locationX_ = value;
        onChanged();
        return this;
      }
      public Builder clearLocationX() {
        bitField0_ = (bitField0_ & ~0x00000002);
        locationX_ = 0D;
        onChanged();
        return this;
      }
      
      // required double locationY = 3;
      private double locationY_ ;
      public boolean hasLocationY() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public double getLocationY() {
        return locationY_;
      }
      public Builder setLocationY(double value) {
        bitField0_ |= 0x00000004;
        locationY_ = value;
        onChanged();
        return this;
      }
      public Builder clearLocationY() {
        bitField0_ = (bitField0_ & ~0x00000004);
        locationY_ = 0D;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:protobuf.ObservationMessage)
    }
    
    static {
      defaultInstance = new ObservationMessage(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:protobuf.ObservationMessage)
  }
  
  public interface ActionOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // required string action = 1;
    boolean hasAction();
    String getAction();
  }
  public static final class Action extends
      com.google.protobuf.GeneratedMessage
      implements ActionOrBuilder {
    // Use Action.newBuilder() to construct.
    private Action(Builder builder) {
      super(builder);
    }
    private Action(boolean noInit) {}
    
    private static final Action defaultInstance;
    public static Action getDefaultInstance() {
      return defaultInstance;
    }
    
    public Action getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return protobuf.PedestrianSim.internal_static_protobuf_Action_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protobuf.PedestrianSim.internal_static_protobuf_Action_fieldAccessorTable;
    }
    
    private int bitField0_;
    // required string action = 1;
    public static final int ACTION_FIELD_NUMBER = 1;
    private Object action_;
    public boolean hasAction() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public String getAction() {
      Object ref = action_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          action_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getActionBytes() {
      Object ref = action_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        action_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    private void initFields() {
      action_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      if (!hasAction()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getActionBytes());
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getActionBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    @java.lang.Override
    protected Object writeReplace() throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static protobuf.PedestrianSim.Action parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static protobuf.PedestrianSim.Action parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.PedestrianSim.Action parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static protobuf.PedestrianSim.Action parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.PedestrianSim.Action parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static protobuf.PedestrianSim.Action parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.PedestrianSim.Action parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static protobuf.PedestrianSim.Action parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static protobuf.PedestrianSim.Action parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static protobuf.PedestrianSim.Action parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(protobuf.PedestrianSim.Action prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements protobuf.PedestrianSim.ActionOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return protobuf.PedestrianSim.internal_static_protobuf_Action_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return protobuf.PedestrianSim.internal_static_protobuf_Action_fieldAccessorTable;
      }
      
      // Construct using protobuf.PedestrianSim.Action.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        action_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protobuf.PedestrianSim.Action.getDescriptor();
      }
      
      public protobuf.PedestrianSim.Action getDefaultInstanceForType() {
        return protobuf.PedestrianSim.Action.getDefaultInstance();
      }
      
      public protobuf.PedestrianSim.Action build() {
        protobuf.PedestrianSim.Action result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private protobuf.PedestrianSim.Action buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        protobuf.PedestrianSim.Action result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public protobuf.PedestrianSim.Action buildPartial() {
        protobuf.PedestrianSim.Action result = new protobuf.PedestrianSim.Action(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.action_ = action_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof protobuf.PedestrianSim.Action) {
          return mergeFrom((protobuf.PedestrianSim.Action)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(protobuf.PedestrianSim.Action other) {
        if (other == protobuf.PedestrianSim.Action.getDefaultInstance()) return this;
        if (other.hasAction()) {
          setAction(other.getAction());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasAction()) {
          
          return false;
        }
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              action_ = input.readBytes();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // required string action = 1;
      private Object action_ = "";
      public boolean hasAction() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public String getAction() {
        Object ref = action_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          action_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setAction(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        action_ = value;
        onChanged();
        return this;
      }
      public Builder clearAction() {
        bitField0_ = (bitField0_ & ~0x00000001);
        action_ = getDefaultInstance().getAction();
        onChanged();
        return this;
      }
      void setAction(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000001;
        action_ = value;
        onChanged();
      }
      
      // @@protoc_insertion_point(builder_scope:protobuf.Action)
    }
    
    static {
      defaultInstance = new Action(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:protobuf.Action)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_protobuf_ObservationMessage_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_protobuf_ObservationMessage_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_protobuf_Action_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_protobuf_Action_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023pedestrianSim.proto\022\010protobuf\"P\n\022Obser" +
      "vationMessage\022\024\n\014pedestrianId\030\001 \002(\t\022\021\n\tl" +
      "ocationX\030\002 \002(\001\022\021\n\tlocationY\030\003 \002(\001\"\030\n\006Act" +
      "ion\022\016\n\006action\030\001 \002(\t"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_protobuf_ObservationMessage_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_protobuf_ObservationMessage_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_protobuf_ObservationMessage_descriptor,
              new java.lang.String[] { "PedestrianId", "LocationX", "LocationY", },
              protobuf.PedestrianSim.ObservationMessage.class,
              protobuf.PedestrianSim.ObservationMessage.Builder.class);
          internal_static_protobuf_Action_descriptor =
            getDescriptor().getMessageTypes().get(1);
          internal_static_protobuf_Action_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_protobuf_Action_descriptor,
              new java.lang.String[] { "Action", },
              protobuf.PedestrianSim.Action.class,
              protobuf.PedestrianSim.Action.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}