package com.myproject.demo.utils;

public class OpCode{

    /**
     *
     */
    private static final long serialVersionUID = -4916005548417920393L;

    public static final int OP_CODE_NULL = -1; // 空操作

    public static final int OP_CODE_LOGIN = 1; // 登录,for pda
    public static final int OP_CODE_SYNC_BASEDATA = 10; // 基础数据下载,for pda

    public static final int OP_CODE_PACKAGE_LABEL = 110; // 建包签
    public static final int OP_CODE_PACKAGE_LOADING = 111; // 装件入包
    public static final int OP_CODE_PACKAGE_UNDO = 112; // 从包中删除,被抽件
    public static final int OP_CODE_TAKINGPACKAGE_LOADING = 114;//收入建包 xielongfei
    public static final int OP_CODE_TAKINGPACKAGE_RULE = 115;//PDA普通面单带出拆包地 xuzhaoxia
    public static final int OP_CODE_PACKAGE_RULE = 113;//PDA电子包签建包 shaoyi
    public static final int OP_CODE_PACKAGE_SEAL = 119; // 封包,预留

    public static final int OP_CODE_CAGE_LABEL = 120; // 建笼签
    public static final int OP_CODE_CAGE_LOADING = 121; // 装件入笼
    public static final int OP_CODE_CAGE_UNDO = 122; // 从笼中删除,被抽件
    public static final int OP_CODE_CAGE_SEAL = 129; // 封签,预留

    public static final int OP_CODE_TRUCK_SORTING = 128; // 上车前的分拣
    public static final int OP_CODE_TRUCK_LABEL = 130; // 建车签
    public static final int OP_CODE_TRUCK_LOADING = 131; // 装件入车,上车
    public static final int OP_CODE_TRUCK_UNDO = 132; // 从车 中删除,被抽件
    public static final int OP_CODE_TRUCKFRQ_LABEL = 133; //PDA上车返回频次   xielongfei
    public static final int OP_CODE_TRUCK_SEAL = 139; // 封车

    public static final int OP_CODE_TRUCK_FLT_LABEL = 135; // 建航空车签,发货
    public static final int OP_CODE_TRUCK_FLT_LOADING = 136; // 航空装件入车,发货上车

    public static final int OP_CODE_TRUCK_FLT_EXCEPTION_LABEL = 137; //航空异常建车签
    public static final int OP_CODE_TRUCK_FLT_EXCEPTION_LOADING = 138; // 航空异常装件入车

    public static final int OP_CODE_TRUCK_DEPART = 140; // 发车

    public static final int OP_CODE_STAYER_IN = 150; // 留仓件入
    public static final int OP_CODE_STAYER_OUT = 155; // 留仓件出,预留

    public static final int OP_CODE_TRUCK_ARRIVE = 160; // 到车
    public static final int OP_CODE_TRUCK_OPEN = 169; //解封

    public static final int OP_CODE_TRUCK_DOWNLABEL = 161; // 下车车签
    public static final int OP_CODE_TRUCK_DOWNING = 162; // 下车

    public static final int OP_CODE_TRUCK_UNSEAL = 170; // 解封车
    public static final int OP_CODE_TRUCK_UNLOADING = 171; // 收入计费(下车)
    public static final int OP_CODE_TRUCK_UNLOADING_UNDO = 172; // 下车 UNDO,预留

    public static final int OP_CODE_TRUCK_FLT_UNSEAL = 175; // 航空解封车提货
    public static final int OP_CODE_TRUCK_FLT_UNLOADING = 176; // 航空下车,提货下车

    public static final int OP_CODE_CAGE_UNSEAL = 190; // 解笼签
    public static final int OP_CODE_CAGE_UNCAGE = 191; // 拆笼
    public static final int OP_CODE_CAGE_UNCAGE_UNDO = 192; // 拆笼 UNDO,预留

    public static final int OP_CODE_PACKAGE_UNSEAL = 180; // 解包签
    public static final int OP_CODE_PACKAGE_UNPACKAGE = 181; // 拆包
    public static final int OP_CODE_PACKAGE_UNPACKAGE_UNDO = 182; // 拆包UNDO,预留

    public static final int OP_CODE_CONTAINER_TAKE_OUT = 185; // 抽件扫描

    public static final int OP_CODE_ISSUEEXP_NOTIFY = 200; // 问题件发布,预留
    public static final int OP_CODE_ISSUEEXP_HANDLE = 210; // 问题件处理发出,预留

    public static final int OP_CODE_ARRESTEXP_NOTIFY = 220; // 通缉件发布,预留
    public static final int OP_CODE_ARRESTEXP_HANDLE = 221; // 通缉件处理,预留

    public static final int OP_CODE_HANDON = 230; // 航空部发出交接,容器(航空部->中心)
    public static final int OP_CODE_HANDON_PACKAGE = 231; // 航空部发出交接,子件(航空部->中心)
    public static final int OP_CODE_HANDON_LABEL = 233; //PDA航空上车返回线路   add by lichuang

    public static final int OP_CODE_HANDON_IN = 235; // 航空部收入交接,容器(中心->航空部)
    public static final int OP_CODE_HANDON_IN_PACKAGE = 236; // 航空部收入交接,子件(中心->航空部)

    public static final int OP_CODE_TRANSIT_AIR_TO_MOT = 237; // 航转汽
    public static final int OP_CODE_TRANSIT_MOT_TO_AIR = 238; // 汽转航

    public static final int OP_CODE_TAKING = 310; // 揽收.揽收表
    public static final int OP_CODE_TAKING_PDA = 311; // PDA揽收.揽收表
    public static final int OP_CODE_TAKING_RECEIVE = 312; // PDA取件揽收整合.揽收表

    public static final int OP_CODE_FOOTBILL = 320; // 底单扫描

    public static final int OP_CODE_BINGING_RECEIPT = 330; // 回单绑定
    public static final int OP_CODE_BINGING_ORDER = 331; // 订单绑定

    public static final int OP_CODE_AGENTEXP = 340; // 代取件扫描

    public static final int OP_CODE_BINGING_CUSTOMER = 332; //直客信息绑定
    public static final int OP_CODE_BINGING_SIGNNAME = 333; //签收人姓名录入

    public static final int OP_CODE_WAYBILL_INPUT = 350; // 录入底单
    public static final int OP_CODE_WAYBILL_INPUTS = 351; // 批量录入底单
    public static final int OP_CODE_WAYBILL_PRE_INPUT = 352; // 录入订单底单

    public static final int OP_CODE_WAYBILL_RECEIPT_INPUT = 360; // 录入回单

    public static final int OP_CODE_WEIGHTBRIDGE = 510; // 次干线车地磅称重(自有车辆-计费-只整车)
    public static final int OP_CODE_WEIGHTBRIDGE_NOFEE = 511; // 主干线车地磅称重(自有车辆-不计费-只整车)
    public static final int OP_CODE_WEIGHTBRIDGE_DOUBLE = 512; // 次干线车地磅称重(社会车辆-计费-需空车回磅)

    public static final int OP_CODE_IUEXP_IMPORT_HANDON = 520; // 中转国际件收入扫描.容器表,预留
    public static final int OP_CODE_IUEXP_IMPORT_HANDON_PACKAGE = 521; // 中转国际件收入扫描.容器表.子件,预留

    public static final int OP_CODE_IUNEXP_EXPORT_HANDON = 910; // 国际部出口件收入.揽收表,预留
    public static final int OP_CODE_IUEXP_EXPORT = 920; // 国际部出口件发出.交接表,预留
    public static final int OP_CODE_IUEXP_IMPORT = 930; // 国际部进口揽收.揽收表,预留

    public static final int OP_CODE_DETAIN_AREO = 950; // 航空安检扣件,预留
    public static final int OP_CODE_DETAIN_CUSTOM = 960; // 海关扣件,预留

    public static final int OP_CODE_ABROAD=999;//国际件海外操作

    public static final int OP_CODE_DELIVERY = 710; // 派件扫描
    public static final int OP_CODE_DELIVERY_PDA = 711; // PDA派件扫描

    public static final int OP_CODE_EXCHANGE = 720; // 换单
    public static final int OP_CODE_IUEXP_EXCHANGE = 729; // 国际件换单

    public static final int OP_CODE_RECEIPTBILL = 730; // 回单核销

    public static final int OP_CODE_SIGNATURE = 740; // 正常签收录入
    public static final int OP_CODE_SIGNATURE_FAILURE = 741; // 失败签收录入
    public static final int OP_CODE_SIGNATURE_PDA = 745; // PDA正常签收
    public static final int OP_CODE_SIGNATURE_PDA_FAILURE = 746; // PDA失败签收

    public static final int OP_CODE_RETURNBILL = 750; // 签单返回

    public static final int OP_CODE_PRIVATEPLANE = 145 ;//专机件接驳扫描

    public static final int OP_CODE_TAIWAN = 888 ;//专机件接驳扫描

    public static final int OP_CODE_POS = 755; // POS刷卡

    public static final int OP_CODE_RETURN_BACK = 831; // 退回操作扫描

    public static final int OP_CODE_THREE_SECTION = 666; //三段码

    public static final int OP_CODE_RETURN_OPERATION = 835; //退回件操作  by wangjun



    //增加海关上下车以及异常扫描
    public static final int OP_CODE_CUSTOMS_TRUCK_LABEL = 530; // 海关上车建车签
    public static final int OP_CODE_CUSTOMS_TRUCK_LOADING = 531; // 海关装件入车,上车
    public static final int OP_CODE_CUSTOMS_TRUCK_UNSEAL = 570; // 海关解封车
    public static final int OP_CODE_CUSTOMS_TRUCK_UNLOADING = 571; // 海关下车
    public static final int OP_CODE_CUSTOMS_EXCEPTION = 444;//海关异常扫描

    /**
     * 增加南航的操作 opCode 从400-410
     */
    public static final int OP_CODE_CSAIR_PDA_HANDON = 400; // 南航PDA收入交接
    public static final int OP_CODE_CSAIR_PDA_INPUT = 402; // 南航PDA收入
    public static final int OP_CODE_CSAIR_PDA_OUT = 404; // 南航PDA发出交接

    /**
     * 增加海外走件的OpCode
     */
    public static final int OP_CODE_OVERSEAS_INPUT = 760; // 海外收入
    public static final int OP_CODE_OVERSEAS_SENDER = 762; // 海外发运
    public static final int OP_CODE_OVERSEAS_CLEAR_NOW = 764; // 海外清关中
    public static final int OP_CODE_OVERSEAS_CLEAR_FINISH = 765; // 海外清关完成
    public static final int OP_CODE_OVERSEAS_HANDON = 767; // 海外派送
    public static final int OP_CODE_OVERSEAS_SIGNATURE = 769; // 海外签收
    public static final int OP_CODE_OVERSEAS_AIRSTART = 771; // 航班已起飞
    public static final int OP_CODE_OVERSEAS_AIRARRIVE = 773; // 航班到达保税区

    /**
     * 设置查询超时时间（秒）/60 = ? 分
     * lh 2013-07-17
     */
    public static final int QUERY_TIME_OUT = 300;



    // 入操作的OP_CODE集合
    public static final int[] OP_CODE_IN = { OP_CODE_STAYER_IN,
            OP_CODE_TRUCK_UNLOADING, OP_CODE_CAGE_UNCAGE,
            OP_CODE_PACKAGE_UNPACKAGE, OP_CODE_TAKING, OP_CODE_TAKING_PDA,
            OP_CODE_IUEXP_IMPORT_HANDON, OP_CODE_IUEXP_IMPORT_HANDON_PACKAGE,
            OP_CODE_IUNEXP_EXPORT_HANDON, OP_CODE_IUEXP_IMPORT,
            OP_CODE_SIGNATURE_FAILURE, OP_CODE_SIGNATURE_PDA_FAILURE,
            OP_CODE_RETURNBILL, OP_CODE_CONTAINER_TAKE_OUT,
            OP_CODE_TRUCK_FLT_UNLOADING };

    // 出操作的OP_CODE集合
    public static final int[] OP_CODE_OUT = { OP_CODE_PACKAGE_LOADING,
            OP_CODE_CAGE_LOADING, OP_CODE_TRUCK_LOADING, OP_CODE_IUEXP_EXPORT,
            OP_CODE_DELIVERY, OP_CODE_DELIVERY_PDA, OP_CODE_SIGNATURE,
            OP_CODE_SIGNATURE_PDA, OP_CODE_TRUCK_FLT_LOADING };

    /**
     * 根据单件的操作码获取对应容器的操作码
     *
     * @param opCode
     * @return
     */
    public static int getContainerOpCode(int opCode) {
        switch (opCode) {
            case OP_CODE_PACKAGE_LOADING:
                return OP_CODE_PACKAGE_LABEL;
            case OP_CODE_PACKAGE_UNDO:
                return OP_CODE_PACKAGE_LABEL;
            case OP_CODE_CAGE_LOADING:
                return OP_CODE_CAGE_LABEL;
            case OP_CODE_CAGE_UNDO:
                return OP_CODE_CAGE_LABEL;
            case OP_CODE_TRUCK_LOADING:
                return OP_CODE_TRUCK_LABEL;
            case OP_CODE_TRUCK_UNDO:
                return OP_CODE_TRUCK_LABEL;

            case OP_CODE_TRUCK_FLT_LOADING:
                return OP_CODE_TRUCK_FLT_LABEL;
            case OP_CODE_TRUCK_FLT_UNLOADING:
                return OP_CODE_TRUCK_FLT_UNSEAL;

            case OP_CODE_TRUCK_UNLOADING:
                return OP_CODE_TRUCK_UNSEAL;
            case OP_CODE_CAGE_UNCAGE:
                return OP_CODE_CAGE_UNSEAL;
            case OP_CODE_PACKAGE_UNPACKAGE:
                return OP_CODE_PACKAGE_UNSEAL;

            case OP_CODE_HANDON_PACKAGE:
                return OP_CODE_HANDON;
            case OP_CODE_HANDON_IN_PACKAGE:
                return OP_CODE_HANDON_IN;

            default:
                return OP_CODE_NULL;
        }
    }

    // 根据发出的操作码获取收入的操作码
    public static int getContainerOutBoundOpCodeByInBoundOpCode(int opCode) {
        switch (opCode) {
            case OP_CODE_PACKAGE_LABEL:
                return OP_CODE_PACKAGE_UNSEAL;
            case OP_CODE_PACKAGE_LOADING:
                return OP_CODE_PACKAGE_UNPACKAGE;
            case OP_CODE_CAGE_LABEL:
                return OP_CODE_CAGE_UNSEAL;
            case OP_CODE_CAGE_LOADING:
                return OP_CODE_CAGE_UNCAGE;
            case OP_CODE_TRUCK_LABEL:
                return OP_CODE_TRUCK_UNSEAL;
            case OP_CODE_TRUCK_LOADING:
                return OP_CODE_TRUCK_UNLOADING;

            case OP_CODE_TRUCK_FLT_LABEL:
                return OP_CODE_TRUCK_FLT_UNSEAL;
            case OP_CODE_TRUCK_FLT_LOADING:
                return OP_CODE_TRUCK_FLT_UNLOADING;

            default:
                return OP_CODE_NULL;
        }
    }
}
