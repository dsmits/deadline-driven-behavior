// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ObservationMessage.proto

package protobuf;

public final class ObservationMessage {
  private ObservationMessage() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ObservationOrBuilder
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
    
    // required bool needNewAction = 4;
    boolean hasNeedNewAction();
    boolean getNeedNewAction();
  }
  public static final class Observation extends
      com.google.protobuf.GeneratedMessage
      implements ObservationOrBuilder {
    // Use Observation.newBuilder() to construct.
    private Observation(Builder builder) {
      super(builder);
    }
    private Observation(boolean noInit) {}
    
    private static final Observation defaultInstance;
    public static Observation getDefaultInstance() {
      return defaultInstance;
    }
    
    public Observation getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return protobuf.ObservationMessage.internal_static_protobuf_Observation_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protobuf.ObservationMessage.internal_static_protobuf_Observation_fieldAccessorTable;
    }
    
    private int bitField0_;
    // required string pedestrianId = 1;
    public static final int PEDESTRIANID_FIELD_NUMBER = 1;
    private java.lang.Object pedestrianId_;
    public boolean hasPedestrianId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public String getPedestrianId() {
      java.lang.Object ref = pedestrianId_;
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
      java.lang.Object ref = pedestrianId_;
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
    
    // required bool needNewAction = 4;
    public static final int NEEDNEWACTION_FIELD_NUMBER = 4;
    private boolean needNewAction_;
    public boolean hasNeedNewAction() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public boolean getNeedNewAction() {
      return needNewAction_;
    }
    
    private void initFields() {
      pedestrianId_ = "";
      locationX_ = 0D;
      locationY_ = 0D;
      needNewAction_ = false;
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
      if (!hasNeedNewAction()) {
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
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBool(4, needNewAction_);
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
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(4, needNewAction_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static protobuf.ObservationMessage.Observation parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static protobuf.ObservationMessage.Observation parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.ObservationMessage.Observation parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static protobuf.ObservationMessage.Observation parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.ObservationMessage.Observation parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static protobuf.ObservationMessage.Observation parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.ObservationMessage.Observation parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static protobuf.ObservationMessage.Observation parseDelimitedFrom(
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
    public static protobuf.ObservationMessage.Observation parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static protobuf.ObservationMessage.Observation parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(protobuf.ObservationMessage.Observation prototype) {
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
       implements protobuf.ObservationMessage.ObservationOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return protobuf.ObservationMessage.internal_static_protobuf_Observation_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return protobuf.ObservationMessage.internal_static_protobuf_Observation_fieldAccessorTable;
      }
      
      // Construct using protobuf.ObservationMessage.Observation.newBuilder()
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
        needNewAction_ = false;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protobuf.ObservationMessage.Observation.getDescriptor();
      }
      
      public protobuf.ObservationMessage.Observation getDefaultInstanceForType() {
        return protobuf.ObservationMessage.Observation.getDefaultInstance();
      }
      
      public protobuf.ObservationMessage.Observation build() {
        protobuf.ObservationMessage.Observation result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private protobuf.ObservationMessage.Observation buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        protobuf.ObservationMessage.Observation result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public protobuf.ObservationMessage.Observation buildPartial() {
        protobuf.ObservationMessage.Observation result = new protobuf.ObservationMessage.Observation(this);
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
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.needNewAction_ = needNewAction_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof protobuf.ObservationMessage.Observation) {
          return mergeFrom((protobuf.ObservationMessage.Observation)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(protobuf.ObservationMessage.Observation other) {
        if (other == protobuf.ObservationMessage.Observation.getDefaultInstance()) return this;
        if (other.hasPedestrianId()) {
          setPedestrianId(other.getPedestrianId());
        }
        if (other.hasLocationX()) {
          setLocationX(other.getLocationX());
        }
        if (other.hasLocationY()) {
          setLocationY(other.getLocationY());
        }
        if (other.hasNeedNewAction()) {
          setNeedNewAction(other.getNeedNewAction());
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
        if (!hasNeedNewAction()) {
          
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
            case 32: {
              bitField0_ |= 0x00000008;
              needNewAction_ = input.readBool();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // required string pedestrianId = 1;
      private java.lang.Object pedestrianId_ = "";
      public boolean hasPedestrianId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public String getPedestrianId() {
        java.lang.Object ref = pedestrianId_;
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
      
      // required bool needNewAction = 4;
      private boolean needNewAction_ ;
      public boolean hasNeedNewAction() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public boolean getNeedNewAction() {
        return needNewAction_;
      }
      public Builder setNeedNewAction(boolean value) {
        bitField0_ |= 0x00000008;
        needNewAction_ = value;
        onChanged();
        return this;
      }
      public Builder clearNeedNewAction() {
        bitField0_ = (bitField0_ & ~0x00000008);
        needNewAction_ = false;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:protobuf.Observation)
    }
    
    static {
      defaultInstance = new Observation(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:protobuf.Observation)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_protobuf_Observation_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_protobuf_Observation_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030ObservationMessage.proto\022\010protobuf\"`\n\013" +
      "Observation\022\024\n\014pedestrianId\030\001 \002(\t\022\021\n\tloc" +
      "ationX\030\002 \002(\001\022\021\n\tlocationY\030\003 \002(\001\022\025\n\rneedN" +
      "ewAction\030\004 \002(\010"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_protobuf_Observation_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_protobuf_Observation_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_protobuf_Observation_descriptor,
              new java.lang.String[] { "PedestrianId", "LocationX", "LocationY", "NeedNewAction", },
              protobuf.ObservationMessage.Observation.class,
              protobuf.ObservationMessage.Observation.Builder.class);
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