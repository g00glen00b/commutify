(function(angular, moment) {
  'use strict';

  function commutifyCalendar() {
    return {
      restrict: 'E',
      require: '^ngModel',
      scope: {
        entries: '='
      },
      templateUrl: 'app/feature-profile/calendar/commutifyCalendar.tpl.html',
      controller: CommutifyCalendarController,
      controllerAs: 'vm',
      bindToController: true,
      link: link
    };

    function link(scope, elem, attrs, ngModel) {
      scope.vm.ngModel = ngModel;

      ngModel.$formatters.unshift(format);

      ////////

      function format(day) {
        scope.vm.date = day;
        return day;
      }
    }
  }

  function CommutifyCalendarController(calendrical) {
    var vm = this;
    vm.weeks = getWeeks();
    vm.monthDay = null;
    vm.isToday = isToday;
    vm.isAfter = isAfter;
    vm.previous = previous;
    vm.next = next;
    vm.selectDay = selectDay;
    vm.activeDay = activeDay;
    vm.getMonthDay = getMonthDay;
    vm.hasEntry = hasEntry;
    vm.getClasses = getClasses;

    ////////

    function getWeeks() {
      return calendrical.getWeeksInMonth(getMonthDay(), { weekStartsWith: 0 });
    }

    function isToday(day) {
      return moment(vm.date).isSame(day.date, 'day');
    }

    function isAfter(day) {
      return moment().isBefore(day.date, 'day');
    }

    function hasEntry(day) {
      var mm = moment(day.date);
      return _.find(vm.entries, function(entry) {
        return mm.isSame(entry.date, 'day');
      }) != null;
    }

    function previous() {
      vm.monthDay = moment(getMonthDay()).subtract(1, 'month').toDate();
      vm.weeks = getWeeks();
    }

    function next() {
      vm.monthDay = moment(getMonthDay()).add(1, 'month').toDate();
      vm.weeks = getWeeks();
    }

    function selectDay(day) {
      if (!day.leadingDay && !day.trailingDay && !isAfter(day)) {
        vm.ngModel.$setViewValue(day.date);
        vm.date = day.date;
        vm.weeks = getWeeks();
      }
    }

    function activeDay() {
      return vm.date || new Date();
    }

    function getMonthDay() {
      return vm.monthDay || new Date();
    }

    function getClasses(day) {
      return {
        'older': day.leadingDay || day.trailingDay || vm.isAfter(day),
        'active': vm.isToday(day),
        'highlight': vm.hasEntry(day)
      };
    }
  }

  CommutifyCalendarController.$inject = ['calendrical'];

  angular.module('commutify.features.profile').directive('commutifyCalendar', commutifyCalendar);
}(angular, moment));