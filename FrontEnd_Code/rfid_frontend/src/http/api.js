const baseUrl = '/api'
export default {

  // 个人中心
  Device: {
    getAllDevice: baseUrl + '/dataAdmin/Equipment/searchEquipmentList',
    addDevice: baseUrl + '/dataAdmin/Equipment/addEquipment',
    // 后加device id
    removeDevice: baseUrl + '/dataAdmin/Equipment/removeEquipment/'
  },
  Connect: {
    // 后加device id
    connect: baseUrl + '/dataAdmin/Equipment/setEquipmentOnline/',
    // 后加device id
    disConnect: baseUrl + '/dataAdmin/Equipment/setEquipmentOffline/'
  }

}
