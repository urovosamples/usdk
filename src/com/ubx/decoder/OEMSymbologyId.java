package com.ubx.decoder;

/*
 * Copyright (C) 2019, Urovo Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @Author: rocky
 * @Date: 20-7-13上午11:21
 */
import android.device.scanner.configuration.Symbology;
import android.text.TextUtils;
import android.util.SparseArray;

public final class OEMSymbologyId {
    static {
        loadHoneyWellTable();
        loadZebraTable();
    }
    //-----------------------------------------------------------------------------
    //  Hand Held Products Symbology ID characters
    //-----------------------------------------------------------------------------
    public static final int HSM_SYMID_AZTEC = 'z';
    public static final int HSM_SYMID_CODABAR = 'a';
    public static final int HSM_SYMID_CODE11 = 'h';
    public static final int HSM_SYMID_CODE128 = 'j';
    public static final int HSM_SYMID_EAN128 = 'I';
    public static final int HSM_SYMID_CODE39 = 'b';
    public static final int HSM_SYMID_CODE49 = 'l';
    public static final int HSM_SYMID_CODE93 = 'i';
    public static final int HSM_SYMID_COMPOSITE = 'y';
    public static final int HSM_SYMID_DATAMATRIX = 'w';
    public static final int HSM_SYMID_EAN8 = 'D';
    public static final int HSM_SYMID_EAN13 = 'd';
    public static final int HSM_SYMID_INT25 = 'e';
    public static final int HSM_SYMID_MAXICODE = 'x';
    public static final int HSM_SYMID_MICROPDF = 'R';
    public static final int HSM_SYMID_PDF417 = 'r';
    public static final int HSM_SYMID_POSTNET = 'P';
    public static final int HSM_SYMID_OCR = 'O';
    public static final int HSM_SYMID_QR = 's';
    public static final int HSM_SYMID_RSS = 'y';
    public static final int HSM_SYMID_UPCA = 'c';
    public static final int HSM_SYMID_UPCE = 'E';
    public static final int HSM_SYMID_ISBT = 'j';
    public static final int HSM_SYMID_BPO = 'B';
    public static final int HSM_SYMID_CANPOST = 'C';
    public static final int HSM_SYMID_AUSPOST = 'A';
    public static final int HSM_SYMID_IATA25 = 'f';
    public static final int HSM_SYMID_CODABLOCK = 'q';
    public static final int HSM_SYMID_JAPOST = 'J';
    public static final int HSM_SYMID_PLANET = 'L';
    public static final int HSM_SYMID_DUTCHPOST = 'K';
    public static final int HSM_SYMID_MSI = 'g';
    public static final int HSM_SYMID_TLC39 = 'T';
    public static final int HSM_SYMID_TRIOPTIC = '=';
    public static final int HSM_SYMID_CODE32 = '<';
    public static final int HSM_SYMID_STRT25 = 'f';
    public static final int HSM_SYMID_MATRIX25 = 'm';
    public static final int HSM_SYMID_PLESSEY = 'n';
    public static final int HSM_SYMID_CHINAPOST = 'Q';
    public static final int HSM_SYMID_KOREAPOST = '?';
    public static final int HSM_SYMID_TELEPEN = 't';
    public static final int HSM_SYMID_CODE16K = 'o';
    public static final int HSM_SYMID_POSICODE = 'W';
    public static final int HSM_SYMID_COUPONCODE = 'c';
    public static final int HSM_SYMID_USPS4CB = 'M';
    public static final int HSM_SYMID_IDTAG = 'N';
    public static final int HSM_SYMID_LABELIV = '>';
    public static final int HSM_SYMID_LABELV = ',';
    public static final int HSM_SYMID_GS1_128 = 'I';
    public static final int HSM_SYMID_HANXIN = 'H';
    public static final int HSM_SYMID_GRIDMATRIX = 'x';

    public static int getHSMSymbologyId(int type) {
        switch (type) {
            case HSM_SYMID_AZTEC:
                return Symbology.AZTEC.toInt();
            case HSM_SYMID_CODABAR:
                return Symbology.CODABAR.toInt();
            case HSM_SYMID_CODE11:
                return Symbology.CODE11.toInt();
            //case HSM_SYMID_ISBT:
            case HSM_SYMID_CODE128:
                return Symbology.CODE128.toInt();
            //case HSM_SYMID_GS1_128  :return Symbology.CODABAR.toInt();
            case HSM_SYMID_GS1_128:
                return Symbology.GS1_128.toInt();
            case HSM_SYMID_CODE39:
                return Symbology.CODE39.toInt();
            //case HSM_SYMID_CODE49:return Symbology.CODABAR.toInt();
            case HSM_SYMID_CODE93:
                return Symbology.CODE93.toInt();
            case HSM_SYMID_DATAMATRIX:
                return Symbology.DATAMATRIX.toInt();
            case HSM_SYMID_EAN8:
                return Symbology.EAN8.toInt();
            case HSM_SYMID_EAN13:
                return Symbology.EAN13.toInt();
            case HSM_SYMID_INT25:
                return Symbology.INTERLEAVED25.toInt();
            //case HSM_SYMID_GRIDMATRIX  :return Symbology.CODABAR.toInt();
            case HSM_SYMID_MAXICODE:
                return Symbology.MAXICODE.toInt();
            case HSM_SYMID_MICROPDF:
                return Symbology.MICROPDF417.toInt();
            case HSM_SYMID_PDF417:
                return Symbology.PDF417.toInt();
            case HSM_SYMID_POSTNET:
                return Symbology.POSTAL_POSTNET.toInt();
            case HSM_SYMID_QR:
                return Symbology.QRCODE.toInt();
            //case HSM_SYMID_COMPOSITE:
            case HSM_SYMID_RSS:
                return Symbology.GS1_14.toInt();
            //case HSM_SYMID_COUPONCODE  :return Symbology.CODABAR.toInt();
            case HSM_SYMID_UPCA:
                return Symbology.UPCA.toInt();
            case HSM_SYMID_UPCE:
                return Symbology.UPCE.toInt();
            case HSM_SYMID_MSI:
                return Symbology.MSI.toInt();
            case HSM_SYMID_TRIOPTIC:
                return Symbology.TRIOPTIC.toInt();
            case HSM_SYMID_CODE32:
                return Symbology.CODE32.toInt();
            //case HSM_SYMID_IATA25 :return Symbology.CODABAR.toInt();
            case HSM_SYMID_IDTAG:
                return Symbology.DISCRETE25.toInt();
            case HSM_SYMID_STRT25:
                return Symbology.DISCRETE25.toInt();
            case HSM_SYMID_MATRIX25:
                return Symbology.MATRIX25.toInt();
            case HSM_SYMID_CHINAPOST:
                return Symbology.CHINESE25.toInt();
            case HSM_SYMID_HANXIN:
                return Symbology.HANXIN.toInt();
            case HSM_SYMID_KOREAPOST:
                return Symbology.CODABAR.toInt();
            case HSM_SYMID_TELEPEN:
                return Symbology.CODABAR.toInt();
            case HSM_SYMID_CODE16K:
                return Symbology.CODABAR.toInt();
            case HSM_SYMID_POSICODE:
                return Symbology.CODABAR.toInt();
            case HSM_SYMID_TLC39:
                return Symbology.CODABAR.toInt();
            case HSM_SYMID_BPO:
                return Symbology.POSTAL_ROYALMAIL.toInt();
            case HSM_SYMID_CANPOST:
                return Symbology.CODABAR.toInt();
            case HSM_SYMID_AUSPOST:
                return Symbology.POSTAL_AUSTRALIAN.toInt();
            case HSM_SYMID_CODABLOCK:
                return Symbology.CODABAR.toInt();
            case HSM_SYMID_JAPOST:
                return Symbology.POSTAL_JAPAN.toInt();
            case HSM_SYMID_PLANET:
                return Symbology.POSTAL_PLANET.toInt();
            case HSM_SYMID_DUTCHPOST:
                return Symbology.POSTAL_UPUFICS.toInt();
            case HSM_SYMID_USPS4CB  :return Symbology.POSTAL_4STATE.toInt();
            //case HSM_SYMID_LABELIV  :return Symbology.CODABAR.toInt();
            //case HSM_SYMID_LABELV  :return Symbology.CODABAR.toInt();
            //case HSM_SYMID_PLESSEY :return Symbology.CODABAR.toInt();
            //case HSM_SYMID_OCR:return Symbology.CODABAR.toInt();
        }
        return Symbology.NONE.toInt();
    }
    public static int getZebraSymbologyId(int type) {
        switch (type) {
            case Zebra_Aztec:
            case Zebra_Aztec_Rune:
                return Symbology.AZTEC.toInt();
            case Zebra_Codabar:
                return Symbology.CODABAR.toInt();
            case Zebra_Code11:
                return Symbology.CODE11.toInt();
            //case HSM_SYMID_ISBT:
            case Zebra_Code128:
                return Symbology.CODE128.toInt();
            //case HSM_SYMID_GS1_128  :return Symbology.CODABAR.toInt();
            case Zebra_EAN128:
            case Zebra_ISBT128:
            case Zebra_CCA_EAN128:
            case Zebra_CCB_EAN128:
            case Zebra_CCC_EAN128:
            case Zebra_ISBT128_Con:
                return Symbology.GS1_128.toInt();
            case Zebra_Code39:
            case Zebra_Code39FullASCII:
                return Symbology.CODE39.toInt();
            case Zebra_Code39_Trioptic:
                return Symbology.TRIOPTIC.toInt();
            //case HSM_SYMID_CODE49:return Symbology.CODABAR.toInt();
            case Zebra_Code93:
                return Symbology.CODE93.toInt();
            case Zebra_DataMatrix:
                return Symbology.DATAMATRIX.toInt();
            case Zebra_EAN8:
            case Zebra_EAN8_2Supplemental:
            case Zebra_EAN8_5supplemental:
            case Zebra_CCA_EAN8:
            case Zebra_CCB_EAN8:
                return Symbology.EAN8.toInt();
            case Zebra_EAN13:
            case Zebra_Bookland:
            case Zebra_ISSN:
            case Zebra_EAN13_2Supplemental:
            case Zebra_EAN13_5supplemental:
            case Zebra_CCA_EAN13:
            case Zebra_CCB_EAN13:
                return Symbology.EAN13.toInt();
            case Zebra_Interleaved_25:
                return Symbology.INTERLEAVED25.toInt();
            //case HSM_SYMID_GRIDMATRIX  :return Symbology.CODABAR.toInt();
            case Zebra_MaxiCode:
                return Symbology.MAXICODE.toInt();
            case Zebra_MicroPDF:
            case Zebra_MicroPDF_CCA:
            case Zebra_Macro_MicroPDF:
                return Symbology.MICROPDF417.toInt();
            case Zebra_PDF417:
                return Symbology.PDF417.toInt();
            case Zebra_PostNet_US:
                return Symbology.POSTAL_POSTNET.toInt();
            case Zebra_QRCode:
            case Zebra_MacroQR:
            case Zebra_MicroQR:
                return Symbology.QRCODE.toInt();
            //case HSM_SYMID_COMPOSITE:
            case Zebra_GS1DataBar14:
            case Zebra_CCA_GS1DataBar14:
            case Zebra_CCB_GS1DataBar14:
            case Zebra_GS1_DatabarCoupon:
                return Symbology.GS1_14.toInt();
            case Zebra_GS1DataBar_Expanded:
            case Zebra_CCA_GS1_DataBarExpanded:
            case Zebra_CCB_GS1DataBar_Expanded:
                return Symbology.GS1_EXP.toInt();
            case Zebra_GS1DataBar_Limited:
            case Zebra_CCA_GS1_DataBarLimited:
            case Zebra_CCB_GS1DataBar_Limited:
                return Symbology.GS1_LIMIT.toInt();
            //case HSM_SYMID_COUPONCODE  :return Symbology.CODABAR.toInt();
            case Zebra_UPCA:
            case Zebra_CCA_UPCA:
            case Zebra_CCB_UPCA:
            case Zebra_UPCA_2Supplemental:
            case Zebra_UPCA_5supplemental:
                return Symbology.UPCA.toInt();
            case Zebra_UPCE0:
            case Zebra_CCA_UPCE:
            case Zebra_CCB_UPCE:
            case Zebra_UPCE0_2Supplemental:
            case Zebra_UPCE0_5supplemental:
                return Symbology.UPCE.toInt();
            case Zebra_UPCE1:
            case Zebra_UPCE1_2Supplemental:
            case Zebra_UPCE1_5supplemental:
                return Symbology.UPCE1.toInt();
            case Zebra_MSI:
                return Symbology.MSI.toInt();
            case Zebra_Code32:
                return Symbology.CODE32.toInt();
            //case HSM_SYMID_IATA25 :return Symbology.CODABAR.toInt();
            case Zebra_Discrete_Standard_25:
                return Symbology.DISCRETE25.toInt();
            case Zebra_Matrix_25:
                return Symbology.MATRIX25.toInt();
            case Zebra_Chinese_25:
                return Symbology.CHINESE25.toInt();
            case Zebra_HanXin:
                return Symbology.HANXIN.toInt();
            case Zebra_Planet_Code:
                return Symbology.POSTAL_PLANET.toInt();

            case Zebra_Korean_35:
                //return Symbology.KoreanPost.toInt();
            /*case Zebra_TLC39:
                return Symbology.COMPOSITE_TLC39.toInt();*/
            //case Zebra_USPS4CB:
                //return Symbology.POSTAL_ROYALMAIL.toInt();
            case Zebra_CanadianPostal:
                //return Symbology.CanadianPostal.toInt();
            case Zebra_Australian_Postal:
                return Symbology.POSTAL_AUSTRALIAN.toInt();
            case HSM_SYMID_CODABLOCK:
                //return Symbology.CODABLOCK.toInt();
            case Zebra_Japan_Postal:
                return Symbology.POSTAL_JAPAN.toInt();
            case Zebra_UK_Postal:
                return Symbology.POSTAL_PLANET.toInt();
            case Zebra_DutchPostal:
                return Symbology.POSTAL_KIX.toInt();
            case Zebra_UPU4State:
                return Symbology.POSTAL_4STATE.toInt();
            case Zebra_USPS4CB:
                return Symbology.POSTAL_UPUFICS.toInt();
        }
        return Symbology.NONE.toInt();
    }
    public static final int Zebra_Code39 = 1;
    public static final int Zebra_Codabar = 2;
    public static final int Zebra_Code128 = 3;
    public static final int Zebra_Discrete_Standard_25 = 4;
    public static final int Zebra_IATA = 5;
    public static final int Zebra_Interleaved_25 = 6;
    public static final int Zebra_Code93 = 7;
    public static final int Zebra_UPCA = 8;
    public static final int Zebra_UPCE0 = 9;
    public static final int Zebra_EAN8 = 10;
    public static final int Zebra_EAN13 = 11;
    public static final int Zebra_Code11 = 12;
    public static final int Zebra_Code49 = 13;
    public static final int Zebra_MSI = 14;
    public static final int Zebra_EAN128 = 15;
    public static final int Zebra_UPCE1 = 16;
    public static final int Zebra_PDF417 = 17;
    public static final int Zebra_Code16K = 18;
    public static final int Zebra_Code39FullASCII = 19;
    public static final int Zebra_UPCD = 20;
    public static final int Zebra_Code39_Trioptic = 21;
    public static final int Zebra_Bookland = 22;
    public static final int Zebra_Coupon_Code = 23;
    public static final int Zebra_NW7 = 24;
    public static final int Zebra_ISBT128 = 25;
    public static final int Zebra_MicroPDF = 26;
    public static final int Zebra_DataMatrix = 27;
    public static final int Zebra_QRCode = 28;
    public static final int Zebra_MicroPDF_CCA = 29;
    public static final int Zebra_PostNet_US = 30;
    public static final int Zebra_Planet_Code = 31;
    public static final int Zebra_Code32 = 32;
    public static final int Zebra_ISBT128_Con = 33;
    public static final int Zebra_Japan_Postal = 34;
    public static final int Zebra_Australian_Postal = 35;
    public static final int Zebra_DutchPostal = 36;
    public static final int Zebra_MaxiCode = 37;
    public static final int Zebra_CanadianPostal = 38;
    public static final int Zebra_UK_Postal = 39;
    public static final int Zebra_MacroPDF = 40;
    public static final int Zebra_MacroQR = 41;
    public static final int Zebra_MicroQR = 44;
    public static final int Zebra_Aztec = 45;
    public static final int Zebra_Aztec_Rune = 46;
    public static final int Zebra_GS1DataBar14 = 48;
    public static final int Zebra_GS1DataBar_Limited = 49;
    public static final int Zebra_GS1DataBar_Expanded = 50;
    public static final int Zebra_USPS4CB = 52;
    public static final int Zebra_UPU4State = 53;
    public static final int Zebra_ISSN = 54;
    public static final int Zebra_Scanlet = 55;
    public static final int Zebra_CueCode = 56;
    public static final int Zebra_Matrix_25 = 57;
    public static final int Zebra_UPCA_2Supplemental = 72;
    public static final int Zebra_UPCE0_2Supplemental = 73;
    public static final int Zebra_EAN8_2Supplemental = 74;
    public static final int Zebra_EAN13_2Supplemental = 75;
    public static final int Zebra_UPCE1_2Supplemental = 80;
    public static final int Zebra_CCA_EAN128 = 81;
    public static final int Zebra_CCA_EAN13 = 82;
    public static final int Zebra_CCA_EAN8 = 83;
    public static final int Zebra_CCA_GS1_DataBarExpanded = 84;
    public static final int Zebra_CCA_GS1_DataBarLimited = 85;
    public static final int Zebra_CCA_GS1DataBar14 = 86;
    public static final int Zebra_CCA_UPCA = 87;
    public static final int Zebra_CCA_UPCE = 88;
    public static final int Zebra_CCC_EAN128 = 89;
    public static final int Zebra_TLC39 = 90;
    public static final int Zebra_CCB_EAN128 = 97;
    public static final int Zebra_CCB_EAN13 = 98;
    public static final int Zebra_CCB_EAN8 = 99;
    public static final int Zebra_CCB_GS1DataBar_Expanded = 100;
    public static final int Zebra_CCB_GS1DataBar_Limited = 101;
    public static final int Zebra_CCB_GS1DataBar14 = 102;
    public static final int Zebra_CCB_UPCA = 103;
    public static final int Zebra_CCB_UPCE = 104;
    public static final int Zebra_Signature_Capture = 105;
    public static final int Zebra_Chinese_25 = 114;
    public static final int Zebra_Korean_35 = 115;
    public static final int Zebra_UPCA_5supplemental = 136;
    public static final int Zebra_UPCE0_5supplemental = 137;
    public static final int Zebra_EAN8_5supplemental = 138;
    public static final int Zebra_EAN13_5supplemental = 139;
    public static final int Zebra_UPCE1_5supplemental = 144;
    public static final int Zebra_Macro_MicroPDF = 154;
    public static final int Zebra_GS1_DatabarCoupon = 180;
    public static final int Zebra_HanXin = 183;
    private static SparseArray<String> zebraTable;
    private static SparseArray<String> honeyWellTable;
    public final static int HoneyWellEngine = 0;
    public final static int ZebraEngine = 1;
    public static String stringFromSymbologyType(int engineType, int symbologyType) {
        if(engineType == HoneyWellEngine) {
            String value = honeyWellTable.get(symbologyType);
            if(TextUtils.isEmpty(value)) {
                return "Undefined";
            } else {
                return value;
            }
        } else if(engineType == ZebraEngine) {
            String value = zebraTable.get(symbologyType);
            if(TextUtils.isEmpty(value)) {
                return "Undefined";
            } else {
                return value;
            }
        } else {
            return "Undefined";
        }
    }
    private static void loadHoneyWellTable(){
        honeyWellTable = new SparseArray<String>();
        honeyWellTable.put(HSM_SYMID_CODE39, "Code 39");
        honeyWellTable.put(HSM_SYMID_CODABAR, "Codabar");
        honeyWellTable.put(HSM_SYMID_CODE128, "Code 128");
        honeyWellTable.put(HSM_SYMID_STRT25, "Discrete (Standard) 2 of 5");
        honeyWellTable.put(HSM_SYMID_IATA25, "IATA");
        honeyWellTable.put(HSM_SYMID_INT25, "Interleaved 2 of 5");
        honeyWellTable.put(HSM_SYMID_CODE93, "Code 93");
        honeyWellTable.put(HSM_SYMID_UPCA, "UPC-A");
        honeyWellTable.put(HSM_SYMID_UPCE, "UPC-E0");
        honeyWellTable.put(HSM_SYMID_EAN8, "EAN-8");
        honeyWellTable.put(HSM_SYMID_EAN13, "EAN-13");
        honeyWellTable.put(HSM_SYMID_CODE11, "Code 11");
        honeyWellTable.put(HSM_SYMID_CODE49, "Code 49");
        honeyWellTable.put(HSM_SYMID_MSI, "MSI");
        honeyWellTable.put(HSM_SYMID_EAN128, "EAN-128");
        ///honeyWellTable.put(HSM_SYMID_UPCE, "UPC-E1");
        honeyWellTable.put(HSM_SYMID_PDF417, "PDF-417");
        honeyWellTable.put(HSM_SYMID_CODE16K, "Code 16K");
        honeyWellTable.put(HSM_SYMID_CODABLOCK, "Codablock");
        honeyWellTable.put(HSM_SYMID_TRIOPTIC, "Code 39 Trioptic");
        honeyWellTable.put(HSM_SYMID_COUPONCODE, "Coupon Code");
        honeyWellTable.put(HSM_SYMID_TELEPEN, "Telepen");
        honeyWellTable.put(HSM_SYMID_ISBT, "ISBT-128");
        honeyWellTable.put(HSM_SYMID_MICROPDF, "Micro PDF");
        honeyWellTable.put(HSM_SYMID_DATAMATRIX, "DataMatrix");
        honeyWellTable.put(HSM_SYMID_QR, "QR Code");
        honeyWellTable.put(HSM_SYMID_POSTNET, "PostNet US");
        honeyWellTable.put(HSM_SYMID_PLANET, "Planet Code");
        honeyWellTable.put(HSM_SYMID_CODE32, "Code 32");
        honeyWellTable.put(HSM_SYMID_JAPOST, "Japan Postal");
        honeyWellTable.put(HSM_SYMID_AUSPOST, "Australian Postal");
        honeyWellTable.put(HSM_SYMID_DUTCHPOST, "Dutch Postal");
        honeyWellTable.put(HSM_SYMID_MAXICODE, "MaxiCode");
        honeyWellTable.put(HSM_SYMID_CANPOST, "Canadian Postal");
        honeyWellTable.put(HSM_SYMID_AZTEC, "Aztec");
        honeyWellTable.put(HSM_SYMID_RSS, "GS1 DataBar");
        honeyWellTable.put(HSM_SYMID_USPS4CB, "USPS 4CB");
        honeyWellTable.put(HSM_SYMID_BPO, "UPU 4State");
        honeyWellTable.put(HSM_SYMID_POSICODE, "PosiCode");
        honeyWellTable.put(HSM_SYMID_MATRIX25, "Matrix 2 of 5");
        honeyWellTable.put(HSM_SYMID_COMPOSITE, "Composite CC-A/B");
        honeyWellTable.put(HSM_SYMID_GRIDMATRIX, "Grid Matrix");
        honeyWellTable.put(HSM_SYMID_GS1_128, "GS1 128");
        honeyWellTable.put(HSM_SYMID_LABELV, "LABELV");
        honeyWellTable.put(HSM_SYMID_LABELIV, "LABELIV");
        honeyWellTable.put(HSM_SYMID_TLC39, "TLC-39");
        honeyWellTable.put(HSM_SYMID_PLESSEY, "Plessey");
        honeyWellTable.put(HSM_SYMID_CHINAPOST, "Chinese 2 of 5");
        honeyWellTable.put(HSM_SYMID_KOREAPOST, "Korean 3 of 5");
        honeyWellTable.put(HSM_SYMID_IDTAG, "Standard 2 of 5");
        honeyWellTable.put(HSM_SYMID_HANXIN, "Han Xin");
        honeyWellTable.put(HSM_SYMID_OCR, "OCR");
    }
    private static void loadZebraTable() {
        zebraTable = new SparseArray<String>();
        zebraTable.put(1, "Code 39");
        zebraTable.put(2, "Codabar");
        zebraTable.put(3, "Code 128");
        zebraTable.put(4, "Discrete (Standard) 2 of 5");
        zebraTable.put(5, "IATA");
        zebraTable.put(6, "Interleaved 2 of 5");
        zebraTable.put(7, "Code 93");
        zebraTable.put(8, "UPC-A");
        zebraTable.put(9, "UPC-E0");
        zebraTable.put(10, "EAN-8");
        zebraTable.put(11, "EAN-13");
        zebraTable.put(12, "Code 11");
        zebraTable.put(13, "Code 49");
        zebraTable.put(14, "MSI");
        zebraTable.put(15, "EAN-128");
        zebraTable.put(16, "UPC-E1");
        zebraTable.put(17, "PDF-417");
        zebraTable.put(18, "Code 16K");
        zebraTable.put(19, "Code 39 Full ASCII");
        zebraTable.put(20, "UPC-D");
        zebraTable.put(21, "Code 39 Trioptic");
        zebraTable.put(22, "Bookland");
        zebraTable.put(23, "Coupon Code");
        zebraTable.put(24, "NW-7");
        zebraTable.put(25, "ISBT-128");
        zebraTable.put(26, "Micro PDF");
        zebraTable.put(27, "DataMatrix");
        zebraTable.put(28, "QR Code");
        zebraTable.put(29, "Micro PDF CCA");
        zebraTable.put(30, "PostNet US");
        zebraTable.put(31, "Planet Code");
        zebraTable.put(32, "Code 32");
        zebraTable.put(33, "ISBT-128 Con");
        zebraTable.put(34, "Japan Postal");
        zebraTable.put(35, "Australian Postal");
        zebraTable.put(36, "Dutch Postal");
        zebraTable.put(37, "MaxiCode");
        zebraTable.put(38, "Canadian Postal");
        zebraTable.put(39, "UK Postal");
        zebraTable.put(40, "Macro PDF");
        zebraTable.put(41, "Macro QR");
        zebraTable.put(44, "Micro QR");
        zebraTable.put(45, "Aztec");
        zebraTable.put(46, "Aztec Rune");
        zebraTable.put(48, "GS1 DataBar-14");
        zebraTable.put(49, "GS1 DataBar Limited");
        zebraTable.put(50, "GS1 DataBar Expanded");
        zebraTable.put(52, "USPS 4CB");
        zebraTable.put(53, "UPU 4State");
        zebraTable.put(54, "ISSN");
        zebraTable.put(55, "Scanlet");
        zebraTable.put(56, "CueCode");
        zebraTable.put(57, "Matrix 2 of 5");
        zebraTable.put(72, "UPC-A + 2 Supplemental");
        zebraTable.put(73, "UPC-E0 + 2 Supplemental");
        zebraTable.put(74, "EAN-8 + 2 Supplemental");
        zebraTable.put(75, "EAN-13 + 2 Supplemental");
        zebraTable.put(80, "UPC-E1 + 2 Supplemental");
        zebraTable.put(81, "CCA EAN-128");
        zebraTable.put(82, "CCA EAN-13");
        zebraTable.put(83, "CCA EAN-8");
        zebraTable.put(84, "CCA GS1 DataBar Expanded");
        zebraTable.put(85, "CCA GS1 DataBar Limited");
        zebraTable.put(86, "CCA GS1 DataBar-14");
        zebraTable.put(87, "CCA UPC-A");
        zebraTable.put(88, "CCA UPC-E");
        zebraTable.put(89, "CCC EAN-128");
        zebraTable.put(90, "TLC-39");
        zebraTable.put(97, "CCB EAN-128");
        zebraTable.put(98, "CCB EAN-13");
        zebraTable.put(99, "CCB EAN-8");
        zebraTable.put(100, "CCB GS1 DataBar Expanded");
        zebraTable.put(101, "CCB GS1 DataBar Limited");
        zebraTable.put(102, "CCB GS1 DataBar-14");
        zebraTable.put(103, "CCB UPC-A");
        zebraTable.put(104, "CCB UPC-E");
        zebraTable.put(105, "Signature Capture");
        zebraTable.put(114, "Chinese 2 of 5");
        zebraTable.put(115, "Korean 3 of 5");
        zebraTable.put(136, "UPC-A + 5 supplemental");
        zebraTable.put(137, "UPC-E0 + 5 supplemental");
        zebraTable.put(138, "EAN-8 + 5 supplemental");
        zebraTable.put(139, "EAN-13 + 5 supplemental");
        zebraTable.put(144, "UPC-E1 + 5 Supplemental");
        zebraTable.put(154, "Macro Micro PDF");
        zebraTable.put(180, "GS1 Databar Coupon");
        zebraTable.put(183, "Han Xin");
    }
}
