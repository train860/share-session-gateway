#!/bin/sh
### BEGIN INIT INFO
# Provides:          shopx
# Required-Start:    $remote_fs $syslog
# Required-Stop:     $remote_fs $syslog
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: Start shopx at boot time
# Description:       Enable service provided by shopx.
### END INIT INFO

### useage
# 将脚本移动到 /etc/init.d/ 目录下：
# sudo mv run.sh /etc/init.d/shopx
# sudo chmod +x /etc/init.d/shopx
# sudo chkconfig --add shopx
# sudo service shopx start

APP_DIR="/nkyapps/gateway"
JAR_PATH="$APP_DIR/jtop-gateway-0.0.1-SNAPSHOT.jar"
JAVA_OPTS="-Xmx4g"  # 根据服务器内存大小进行调整
QUARKUS_PROFILE="prod"
PORT=9090

start() {
    echo "Starting jtop gateway..."
    nohup java $JAVA_OPTS -jar $JAR_PATH > /dev/null 2>&1 &
}

stop() {
    echo "Stopping jtop gateway..."
    PID=$(lsof -t -i :9090)
    if [ ! -z "$PID" ]; then
        kill "$PID"
        echo "jtop gateway stopped."
    else
        echo "jtop gateway is not running."
    fi
}

status() {
    PID=$(lsof -t -i :9090)
    if [ ! -z "$PID" ]; then
        echo "jtop gateway is running (PID: $PID)."
    else
        echo "jtop gateway is not running."
    fi
}

case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        stop
        sleep 2
        start
        ;;
    status)
        status
        ;;
    *)
        echo "Usage: $0 {start|stop|restart|status}"
        exit 1
        ;;
esac

exit 0
