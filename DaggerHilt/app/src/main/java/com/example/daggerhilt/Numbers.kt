package com.example.daggerhilt

import com.google.gson.annotations.SerializedName

data class Numbers(var id : String,
    var num : String)


data class PreRegistraionResponse(@SerializedName("status") var status : Boolean?= null,
                                  @SerializedName("response") var response : PreRegistration?= PreRegistration(),
                                  @SerializedName("error") var error : String? = null)


data class PreRegistration(@SerializedName("count") var count : String? = null,
                           @SerializedName("next_page_status") var nextpagestatus : Int? = null,
                           @SerializedName("consumers") var consumers : ArrayList<PreRegistraionConsumers> = arrayListOf())


data class PreRegistraionConsumers(@SerializedName("id") var id : String? = null,
                                   @SerializedName("code") var code : String? = null,
                                   @SerializedName("name") var name : String? = null,
                                   @SerializedName("mobile") var mobile : String? = null,
                                   @SerializedName("state") var state : String? = null,
                                   @SerializedName("district") var district : String? = null,
                                   @SerializedName("location") var location : String? = null,
                                   @SerializedName("created_date") var createdDate : String? = null,
                                   @SerializedName("created_by") var createdBy : String? = null,
                                   @SerializedName("payment_status") var paymentStatus : String? = null)

data class PreregProfileResponse(@SerializedName("status") var status : Boolean?  = null,
                                 @SerializedName("response") var response : PreregistrationC? = PreregistrationC(),
                                 @SerializedName("error") var error : String?   = null)

data class PreregistrationC(@SerializedName("consumer" ) var consumerProfile: PreregistraionProfile = PreregistraionProfile())

data class PreregistraionProfile(@SerializedName("id") var id : String?  = null,
                                 @SerializedName("code") var code : String?  = null,
                                 @SerializedName("title") var title : String?  = null,
                                 @SerializedName("name") var name : String? = null,
                                 @SerializedName("relation" ) var relation : String? = null,
                                 @SerializedName("relative") var relative : String? = null,
                                 @SerializedName("aadhar" ) var aadhar : String? = null,
                                 @SerializedName("email") var email : String? = null,
                                 @SerializedName("mobile") var mobile : String? = null,
                                 @SerializedName("alt_mobile") var altMobile : String? = null,
                                 @SerializedName("state" ) var state : String? = null,
                                 @SerializedName("district" ) var district : String? = null,
                                 @SerializedName("location" ) var location : String? = null,
                                 @SerializedName("geo_area" ) var geoArea : String? = null,
                                 @SerializedName("address1" ) var address1 : String? = null,
                                 @SerializedName("address2" ) var address2 : String? = null,
                                 @SerializedName("colony" ) var colony : String? = null,
                                 @SerializedName("city" ) var city : String? = null,
                                 @SerializedName("landmark" ) var landmark : String? = null,
                                 @SerializedName("ward_no" ) var wardNo : String? = null,
                                 @SerializedName("pincode" ) var pincode : String? = null,
                                 @SerializedName("note" ) var note  : String? = null,
                                 @SerializedName("status" ) var status : String? = null,
                                 @SerializedName("scheme" ) var scheme : String? = null,
                                 @SerializedName("payment" ) var payment : String? = null,
                                 @SerializedName("security_deposit" ) var securityDeposit : String? = null,
                                 @SerializedName("registration_charges" ) var registrationCharges : String? = null,
                                 @SerializedName("consumption_deposit"  ) var consumptionDeposit  : String? = null,
                                 @SerializedName("total_deposit") var totaldeposit : String? = null,
                                 @SerializedName("total") var total : String? = null,
                                 @SerializedName("lpg_no") var lpg : String? = null,
                                 @SerializedName("created_by" ) var createdBy : String? = null,
                                 @SerializedName("created_date" ) var createdDate : String? = null,
                                 @SerializedName("payment_status" ) var paymentStatus : String? = null,
                                 @SerializedName("district_id" ) var districtId : String? = null,
                                 @SerializedName("documents" ) var documents : ArrayList<PreRegDocuments> = arrayListOf(),
                                 @SerializedName("payments" ) var payments : ArrayList<PaymentsP> = arrayListOf())

data class PaymentsP(@SerializedName("amount" ) var amount : String? = null,
                     @SerializedName("transaction_no") var transactionNo : String? = null,
                     @SerializedName("note" ) var note : String? = null,
                     @SerializedName("payment_type" ) var paymentType : String? = null,
                     @SerializedName("payment_date" ) var paymentDate : String? = null)

data class PreRegDocuments(@SerializedName("consumer") var consumer : String? = null,
                           @SerializedName("name")var name : String? = null,
                           @SerializedName("document_type")var document_type : String? = null,
                           @SerializedName("reason") var reason : String? = null)


sealed class NetworkResult<T> {
    data class Loading<T>(val isLoading: Boolean) : NetworkResult<T>()
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failure<T>(val errorMessage: String) : NetworkResult<T>()
}